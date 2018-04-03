<%@ page import="com.javaweb.listener.OnlineListener" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>MVC</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="CSS/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h5>在线人数: <%=OnlineListener.getCount()%></h5>
    <h5>在线用户：</h5>
    <%
        for (Object obj:OnlineListener.online()) {
            out.println(obj+"<br>");
        }
    %>
</div>
</body>
</html>
