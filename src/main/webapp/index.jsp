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
    <h1>基于 MVC 的 Java Web 开发</h1>
    <ul>
        <li><a href="CarServlet">Car Manager System</a></li>
        <li><a href="ContactServlet">Contact</a></li>
    </ul>
    <form action="LoginServlet" method="post" name="f">
        <input type="hidden" name="action" value="login">
        <div class="form-group">
            <label for="username" class="col-form-label">用户名</label>
            <input type="text" name="username" id="username" placeholder="用户名" class="form-control">
        </div>

        <div class="form-group">
            <label for="password" class="col-form-label">密码</label>
            <input type="password" name="password" id="password" placeholder="密码" class="form-control">
        </div>
        <button type="submit" class="btn-outline-primary">登录</button>
    </form>
</div>
</body>
</html>
