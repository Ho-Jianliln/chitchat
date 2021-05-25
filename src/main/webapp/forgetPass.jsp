<%--
  Created by IntelliJ IDEA.
  User: Jerry Ho
  Date: 2021/5/10
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
    <head>
        <title>忘记密码</title>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <!-- 1. 导入CSS的全局样式 -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
        <!-- 3. 导入bootstrap的js文件 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    </head>
    <body>
        <h1 style="text-align: center;">ChitChat忘记密码重置</h1>
        <div style="text-align: center;">
            <label for="account">账号：</label>
            <input type="text" name="account" id="account" class="form" placeholder="至少4位字母或数字">
        </div>
        <div style="text-align: center;">
            <label for="emailCode">邮箱验证码：</label>
            <input type="text" name="emailCode" id="emailCode" class="form" >
            <input type="button" value="发送验证码" id="sendEmailCode" onclick="sendEmailCode()">
        </div>
        <div style="text-align: center;">
            <img src="${pageContext.request.contextPath}/VcodeServlet" onclick="refreshVcode()" id="vcode"/>
            <input type="text" name="vcodecheck" id="vcodecheck" class="form" placeholder="请输入验证码">
        </div>
        <div style="text-align: center;">
            <label for="password1">重置密码：</label>
            <input type="password" name="password" id="password1" class="form" placeholder="至少6位至多16位字母或数字">
        </div>
        <div style="text-align: center;">
            <label for="password2">再次输入密码：</label>
            <input type="password" name="password" id="password2" class="form" placeholder="至少6位至多16位字母或数字">
        </div>
        <div style="text-align: center;">
            <input type="button" value="重置密码" id="resetPass" onclick="resetPass()">
        </div>
    </body>
    <script>
        var sendEmailCount=0;
        var sendEmailLimit=0;
        var testAccount=/^[a-zA-Z0-9]{4,20}$/;
        var testPassword=/^[a-zA-Z0-9]{4,16}$/;
        var testEmail = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.]){1,2}[A-Za-z\d]{2,5}$/g;
        function sendEmailCode() {
            if(!testAccount.test($("#account").val())){
                alert("账号格式错误！");
            }else if(sendEmailLimit==0) {
                $.post("FrontServlet", {account:$("#account").val(),method:"sendresetPassEmail"}, function (data) {
                    sendEmailCount++;
                    sendEmailLimit = 1;
                });
                setTimeout(function() {sendEmailLimit = 0}, 1000 * 60);
            }else{
                alert("请一分钟后再发送");
            }
        }
        function resetPass(){
            if(!testAccount.test($("#account").val())){
                alert("账号格式错误！");
            }else if (!testPassword.test($("#password1").val())||!testPassword.test($("#password2").val())
                ||!($("#password1").val()===$("#password2").val())){
                alert("密码格式错误！或两次密码不一致");
            }else if(sendEmailCount==0||$("#emailCode").val()===""){
                alert("未发送邮箱验证码 或未填邮箱验证码");
            }else if($("#vcodecheck").val()===""){
                alert("未填验证码");
            }else{
                submit();
            }
        }
        function submit(){
            var data={account:$("#account").val(),password:$("#password2").val(),email:$("#email").val(),
                vcodecheck:$("#vcodecheck").val(),emailCode:$("#emailCode").val(),method:"resetPass"}
            $.post("FrontServlet",data,function (data){
                alert(String(data));
                refreshVcode();
            });
        }
        function refreshVcode(){
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode");
            //2.设置其src属性，加时间戳
            vcode.src = "${pageContext.request.contextPath}/VcodeServlet?time="+new Date().getTime();
        }
    </script>
</html>
