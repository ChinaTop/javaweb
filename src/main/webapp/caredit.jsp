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
    <c:if test="${empty car}">
        <h4>新增小汽车</h4>
    </c:if>
    <c:if test="${!empty car}">
        <h4>修改小汽车</h4>
    </c:if>

    <form action="CarServlet" method="post" name="f">
        <input type="hidden" name="action" value="save">
        <input type="hidden" name="id" value="${car.id}">
        <div class="form-group">
            <label class="col-form-label" for="name">车名</label>
            <input id="name" name="name" placeholder="车名" class="form-control" type="text"
                   value="${car.name}">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="price">价格</label>
            <input id="price" name="price" placeholder="价格" class="form-control" type="number"
                   step="0.01" value="${car.price}">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="createDate">生产日期</label>
            <input id="createDate" name="createDate" placeholder="生产日期" class="form-control"
                   type="date" value="${car.createDate}">
        </div>

        <button type="submit" class="btn btn-success btn-block">保存</button>
        <button type="reset" class="btn btn-warning btn-block">重置</button>
    </form>
    </div>
</body>
</html>
