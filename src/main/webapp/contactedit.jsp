<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="CSS/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>基于 MVC 的 Java Web 开发</h1>
    <c:if test="${empty contact}">
        <h4>新增联系人</h4>
    </c:if>
    <c:if test="${!empty contact}">
        <h4>修改联系人</h4>
    </c:if>

    <form action="ContactServlet" method="post" name="f">
        <input type="hidden" name="action" value="save">
        <input type="hidden" name="id" value="${contact.id}">
        <div class="form-group">
            <label class="col-form-label" for="name">姓名</label>
            <input id="name" name="name" placeholder="姓名" class="form-control" type="text"
                   value="${contact.name}">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="mobil">手机</label>
            <input id="mobil" name="mobil" placeholder="手机" class="form-control" type="text"
                   value="${contact.mobil}">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="org">单位</label>
            <input id="org" name="org" placeholder="单位" class="form-control" type="text"
                   value="${contact.org}">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="phone">办公电话</label>
            <input id="phone" name="phone" placeholder="办公电话" class="form-control" type="text"
                   value="${contact.phone}">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="email">Email</label>
            <input id="email" name="email" placeholder="Email" class="form-control" type="text"
                   value="${contact.email}">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="address">地址</label>
            <input id="address" name="address" placeholder="地址" class="form-control" type="text"
                   value="${contact.address}">
        </div>

        <button type="submit" class="btn btn-success btn-block">保存</button>
        <button type="reset" class="btn btn-warning btn-block">重置</button>
    </form>
</div>

</body>
</html>
