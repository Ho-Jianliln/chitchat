<%--
  Created by IntelliJ IDEA.
  User: Jerry Ho
  Date: 2021/5/7
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
    <head>
        <title>欢迎来到ChitChat</title>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    </head>
    <body>
        <h1 style="text-align: center;">ChitChat登录</h1>
        <div style="text-align: center;">
            <label for="account">账号：</label>
            <input type="text" name="account" id="account" class="form" placeholder="请输入账号">
        </div>
        <div style="text-align: center;">
            <label for="password">密码：</label>
            <input type="password" name="password" id="password" class="form" placeholder="请输入密码">
        </div>
        <div style="text-align: center;">
            <img src="${pageContext.request.contextPath}/VcodeServlet" onclick="refreshVcode()" id="vcode"/>
            <input type="text" name="vcodecheck" id="vcodecheck" class="form" placeholder="请输入验证码">
        </div>
        <div style="text-align: center;">
            <p><input type="checkbox" id="autoLogin"  /> 自动登录 </p>
            <p><input type="checkbox" id="isAdmin"  /> 管理员</p>
        </div>
        <div style="text-align: center;">
            <a href="forgetPass.jsp">忘记密码</a>
            <a href="register.jsp">注册</a>
        </div>
        <div style="text-align: center;">
            <input type="button" value="登录" id="loginBut" onclick="submit()">
        </div>
    </body>
    <script>
        function submit(){
            var data={method:"login",account:$("#account").val(),
                password:$("#password").val(),vcodecheck:$("#vcodecheck").val(),
                autoLogin:$('#autoLogin').prop('checked'),isAdmin:$('#isAdmin').prop('checked')}
            $.post("FrontServlet",data,function (data){
                if(data==="user") {
                    window.location="menu.jsp"
                }else if(data==="admin"){
                    window.location="adminMenu.jsp"
                }else{
                    alert(String(data));
                    refreshVcode();
                }
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
