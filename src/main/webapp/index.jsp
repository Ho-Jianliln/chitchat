<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>欢迎访问ChitChat</title>
</head>
<body>
<h1><%= "即将跳转登录界面..." %>
</h1>
<br/>
    <script language="JavaScript">
        function redirct(){
            window.location.href="login.jsp"
        }
        setTimeout(redirct,1000);
    </script>
</body>
</html>