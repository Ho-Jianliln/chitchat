<%--
  Created by IntelliJ IDEA.
  User: Jerry Ho
  Date: 2021/5/10
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
    <head>
        <title>ChitChat主页</title>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">ChitChat</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li ><a href="#chat" data-toggle="tab" onclick="refreshFriendList()">聊天</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">关系<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#friendList" data-toggle="tab" onclick="refreshFriendList()">好友列表</a></li>
                                <li><a href="#groupList" data-toggle="tab">群聊列表</a></li>
                                <li><a href="#add" data-toggle="tab">+好友/群聊</a></li>
                                <li><a href="#request" data-toggle="tab" onclick="refreshRequest()">查看请求</a></li>
                            </ul>
                        </li>
                        <li><a href="#moments" data-toggle="tab">朋友圈</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right" id="myInf">
                        <li>
                            <a id="welcome" data-toggle="tab"></a>
                            <img id="myHeadshot" class="img-circle" scr="" sizes="">
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
                <div class="tab-content">
                    <div class="tab-pane fade" id="chat" style="min-height: 530px"><!--聊天界面-->
                        <div class="row pre-scrollable " style="min-height:530px;width:250px;float: left">
                            <ul class="nav nav-pills nav-stacked" id="chatList" style="min-height:800px"><!--聊天对像列表-->
                            </ul>
                        </div>
                        <div style="float: right;width: 75%">
                            <fieldset>
                                <div class="tab-content" style="height: 60%" id="chatRoom"><!--聊天内容-->
                                    <span class="empty">-暂无消息-</span>
                                </div>
                            </fieldset>
                            <div style="float:bottom"><!--输入聊天内容-->
                                <button type="button" class="btn btn-primary btn-sm">发送图片</button>
                                <button type="button" class="btn btn-primary btn-sm">发送文件</button>
                                <button type="button" class="btn btn-primary btn-sm">表情</button>
                                <form role="form">
                                    <div class="form-group">
                                        <textarea class="form-control" rows="4" id="msgContext"></textarea>
                                    </div>
                                    <input class="btn btn-default" type="submit" value="发送" onclick="sendMsg()">
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="friendList">
                        <ul class="list-group" id="friendListItem">
                        </ul>
                    </div>
                    <div class="tab-pane fade" id="groupList">ggggggg</div>
                    <div class="tab-pane fade" id="add">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <input id="goalFriend" type="text" class="form-control" placeholder="通过账号或名称寻找好友">
                                    <span class="input-group-btn">
                                        <button onclick="searchFriend()" class="btn btn-default" type="button">搜索</button>
                                    </span>
                                </div><!-- /input-group -->
                            </div>
                            <div class="col-lg-6">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="通过群号寻找群聊">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="button">搜索</button>
                                    </span>
                                </div><!-- /input-group -->
                            </div>
                        </div>
                        <div class="row pre-scrollable"  style="min-height:480px"><!--搜索结果-->
                            <ul class="list-group" id="findResult" type="">
                            </ul>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="request"><!--请求列表-->
                        <ul class="list-group" id="requestList" type=""></ul>
                    </div>
                    <div class="tab-pane fade" id="moments">pyq</div>
                </div>
            </div><!-- /.container-fluid -->
            </div>
        </nav>
    </body>

    <script>
        var myId;
        var myName;
        var friendListData;
        var toId;
        var toName;
        var websocket = null;
        $(function (){
            $.post("FrontServlet?method=getAccount",function (data){
                var user=JSON.parse(data);
                myId=user.id;
                myName=user.name;
                $("#welcome").text("欢迎，"+user.name);
                $("#myHeadshot").scr=user.headshot;
            })
            loadChatBox();
        })
        function init(){}
        if (window.WebSocket) {
            websocket = new WebSocket("ws://localhost:8080/chitchat/chatWebsocket/"+myId);
            websocket.onopen = function() {
                console.log('已连接');
            };
            websocket.onerror = function() {
                console.log('连接发生错误');
            };
            websocket.onclose = function() {
                console.log('已经断开连接');
            };
            // 消息接收
            websocket.onmessage = function(message) {
                var jsonMsg=JSON.parse(message.data)
                // 修改未读
                $.post("UnReadMsg",
                    {"fromId":jsonMsg.toId, "toId": jsonMsg.fromId},
                    function(data) {}
                );
                // 通知对方已读
                $.post("IsReadMsg",{"fromId":jsonMsg.toId, "toId": jsonMsg.fromId, "status": 1})
                appendMsg(JSON.parse(message.data),$("[room='"+jsonMsg.toId+"']"));
            };
        } else {
            alert("建议使用高版本的浏览器，<br/>");
        }
        function sendMsg(){
            var msgContext=$("#msgContext").val()
            if(msgContext){
                var msg={"fromId":myId, "toId":toId, "context":msgContext, "status":0};
                websocket.send(JSON.stringify(msg));
                var date = new Date().toLocaleString();// 拼接我的消息 可以返回服务器时间
                var my_msg = {"fromId":myId,"context":msgContext,"date":date,"toId":toId, "flag":true};
                appendMsg(my_msg, $("[room='"+toId+"']"));
            }
        }
        // 添加消息到div
        function appendMsg(msgs, listElement) {
            if (msgs.length > 0)
                listElement.find("span").remove();

            if (!msgs.length) {
                if (msgs.length == 0) return;
                // 发送过来的单条消息
                var ele = $("<div>" + "<div class=\"" + "left_title\">"
                    + msgs.toId + "</div>"
                    + "<div class=\"" + "left_time\">" + msgs.date
                    + "</div>" + "<div class=\"" + "left_msg\">"
                    + msgs.context + "</div>" + "</div>");
                if (msgs.flag) {
                    ele = $("<div>" + "<div class=\"" + "right_title\">"
                        + msgs.fromId + "</div>"
                        + "<div class=\"" + "right_time\">" + msgs.date
                        + "</div>"
                        + "</div>" + "<div class=\"" + "right_msg\">"
                        + msgs.context + "</div>"
                        + "<div class='right_time'> <label class='isRead'>未读</label> </div>"
                        + "</div>");
                }
                listElement.append(ele);
            } else {
                // 历史
                for (var i = 0; i < msgs.length; i++) {
                    var msg = msgs[i];
                    var dir = "left";// 区分历史消息左右
                    var person = toId;// 区分左右是谁
                    if (msg.fromId == toId) {
                        // 发给我的消息
                        dir = "left";
                        person = toName;
                    } else {
                        dir = "right";
                        person = myName;
                    }
                    var ele = "<div>" + "<div class=\"" + dir + "_title\">"
                        + person + "</div>"
                        + "<div class=\"" + dir + "_time\">" + msg.date
                        + "</div>" + "<div class=\"" + dir + "_msg\">"
                        + msg.context + "</div>" + "</div>";
                    // 拼接未读
                    if (msg.status == 0) {
                        ele += "<div class='right_time'><label class='isRead'>未读</label></div>"
                    }
                    ele = $(ele);
                    listElement.append(ele);
                }
            }
            var x = listElement[0].scrollHeight - listElement[0].scrollTop;
            //滚动条需要滚动的大小(底部)
            var position = listElement[0].scrollHeight - listElement.height();
            //JQuery动画
            listElement.animate({
                scrollTop : position
            }, (x / 100 + 1) * 50, function() {
                //当前动画执行完成之后
            });
        }
        function refreshRequest() {
            $("#requestList").empty();
            $.post("RelationServlet?method=checkRequest",{myId:myId},function (data){
                if(data==0){$("#request").text("无新请求")}
                else {
                    var json=JSON.parse(data);
                    for(var i=0;i<json.length;i++){
                        var requestData=json[i];
                        var request="<li class=\"list-group-item\" name=\"list-group-item\" name=\"request\" id="+requestData.id+">";
                        if(requestData.type==1){<!--好友申请-->
                            if(requestData.applicant==myId){<!--我是发起人-->
                                request+="申请添加："+requestData.target+"为好友，申请理由："+requestData.context+ "状态：";
                                switch (requestData.status) {
                                    case 0:request+="对方未处理";break;
                                    case 1:request+="对方已接受 <input class=\"btn btn-default\" type=\"button\" value=\"确认\" onclick=\"confirm(this)\">";break;
                                    case 2:request+="对方已拒绝 <input class=\"btn btn-default\" type=\"button\" value=\"确认\" onclick=\"confirm(this)\">";break;
                                }
                            }else if(requestData.status==0){<!--我是接收人且请求未处理-->
                                request+=requestData.applicant+"申请添加你为好友，申请理由："+requestData.context+
                                    "<input class=\"btn btn-default\" type=\"button\" value=\"接受\" onclick=\"acceptFriend(this)\">" +
                                    "<input class=\"btn btn-default\" type=\"button\" value=\"拒绝\" onclick=\"rejectFriend(this)\">";
                            }
                        }
                        else if(requestData.type==2){}<!--加群申请-->
                        else if(requestData.type==3){}<!--举报-->
                        request+="</li>";
                        $("#requestList").append(request);
                    }
                }
            })
        }
        function confirm(btn){
            var request_id=btn.parentNode.getAttribute("id");
            $.post("RelationServlet?method=confirm",{request_id:request_id},function (data){
                if(data==1){
                    btn.setAttribute("disabled","disabled");
                    btn.setAttribute("value","已确认");
                }
            })
        }
        function acceptFriend(btn) {
            var request_id=btn.parentNode.getAttribute("id");
            var nickname=prompt("为朋友设置昵称");
            $.post("RelationServlet?method=addFriend",{request_id:request_id,nickname:nickname},function (data){
                if(data==2){
                    alert("添加好友成功");
                    refreshFriendList();
                }
                else{alert("添加好友失败")}
            })
        }
        function rejectFriend(btn) {
            var request_id=btn.parentNode.getAttribute("id");
            $.post("RelationServlet?method=rejectFriend",{request_id:request_id},function (data){
                if(data==1){alert("已拒绝好友申请")}
                else {alert("拒绝失败")}
            })
        }
        function refreshFriendList(){
            $.post("RelationServlet?method=friendList",{my_id:myId},function (data){
                if(data==0){$("#friendList").text("好友列表为空")}
                else{
                    friendListData=JSON.parse(data);
                    setFriendList();
                    setChatList()
                }
            })
        }
        function setFriendList(){
            $("#friendListItem").empty();
            for (var i=0;i<friendListData.length;i++){
                var friendData=friendListData[i];
                if(friendData.nickname==""){friendData.nickname=friendData.name}
                var friendItem="<li class=\"list-group-item\" name=\"friendItem\" id=\""+friendData.id+"\">"+
                    "<img id=\"Headshot\" class=\"img-circle\" scr=\"\" sizes=\"\">"+friendData.nickname+"<div id=\"online\"></div>"+
                    "<input class=\"btn btn-default\" type=\"button\" value=\"修改昵称\" onclick=\"setNickname(this)\">"+
                    "<input class=\"btn btn-default\" type=\"button\" value=\"删除好友\" onclick=\"delFriend(this)\">"+"</lil>";
                $("#friendListItem").append(friendItem);
            }
        }
        function setChatList() {
            $("#chatList").empty();
            for (var i=0;i<friendListData.length;i++){
                var friendData=friendListData[i];
                if(friendData.nickname==""){friendData.nickname=friendData.name}
                var friendItem="<li class=\"list-group-item\">"+"<a href=\"#"+friendData.id+"\"  name=\""+friendData.nickname+
                    "\" onclick=\"loadChatRoom(this)\" data-toggle='tab'>" +"<img id=\"Headshot\" class=\"img-circle\" scr=\"\" sizes=\"\">"
                    +friendData.nickname+"<div id=\"online\"></div>"+"</a></lil>";
                $("#chatList").append(friendItem);
            }
        }
        function loadChatRoom(li) {
            toId=li.getAttribute("href").substr(1);
            toName=li.getAttribute("name");
            if($("#chatRoom"))
            var chatRoom="<div room=\""+toId+"\">";
            $("#chatRoom").append(chatRoom);
        }
        function setNickname(btn){
            var friendId=btn.parentNode.getAttribute("id");
            var newNickname=prompt("请设置新昵称");
            $.post("RelationServlet?method=setNickname",{my_id:myId,friend_id:friendId,nickname:newNickname,black:0},function (data){
                if(data==1){
                    for(var i=0;i<friendListData.length;i++){
                        if(friendListData[i].id==friendId) friendListData[i].nickname=newNickname;
                    }
                    setFriendList();
                }else{alert("修改昵称失败")}
            })
        }
        function delFriend(btn){
            var friendId=btn.parentNode.getAttribute("id");
            if(confirm("确定要删除好友吗")==true){
                $.post("RelationServlet?method=delFriend",{my_id:myId,friend_id:friendId},function (data){
                    if(data==1){
                        for(var i=0;i<friendListData.length;i++){
                            if(friendListData[i].id==friendId){
                                friendListData.slice(i,1);
                            }
                        }
                    }else{alert("删除好友失败")}
                })
            }
        }
        function searchFriend(){
            var searchContext=$("#goalFriend").val();
            if(searchContext!=null&&searchContext!=""){
                $.post("RelationServlet",{goal:searchContext,method:"findFriend"},function (data){
                    $("#findResult").empty();
                    if(data=="0"){$("#findResult").text("找不到该帐户")}
                    else {
                        var users=JSON.parse(data);
                        for(var i=0;i<users.length;i++){
                            var json=users[i];
                            var user="<li class=\"list-group-item\" name=\"friend\" id=\""+json.id+"\">"+
                                "账号："+json.id+"    名称："+json.name+"    上次登录："+json.lastTime+
                                "<input class=\"btn btn-default\" type=\"button\" value=\"申请成为朋友\" onclick=\"addFriend(this)\"> </lil>";
                            $("#findResult").append(user);
                        }
                    }
                })
            }else {alert("输入为空");}
        }
        function addFriend(btn){
            var id=btn.parentNode.getAttribute("id");
            var context=prompt("请输入申请信息");
            var json={applicant:myId,target:id,context:context,type:"1",status:"0"};
            if(context!=null&&context!=""){
                $.post("RelationServlet?method=addFriendReq",json,function (data){
                    if (Number(data)==1){alert("请求发送成功")}
                    else{alert("请求发送失败")}
                })
            }
        }
        function joinGroup(){}
    </script>
</html>
