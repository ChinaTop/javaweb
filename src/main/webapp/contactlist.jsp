<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <h1>个人通讯录</h1>
    <div class="form-group row">
        <form action="ContactServlet" method="post" name="f">
            <input type="hidden" name="action" value="list">
            <input type="text" name="name" placeholder="姓名" class="form-control">
            <input type="text" name="mobil" placeholder="手机"  class="form-control">
            <button type="submit" class="btn btn-primary">查询</button>
            &nbsp;&nbsp;
            <a href="contactedit.jsp">新增</a>
        </form>
    </div>

    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <td>No.</td>
            <td>姓名</td>
            <td>手机</td>
            <td>单位</td>
            <td>办公电话</td>
            <td>EMAIL</td>
            <td>地址</td>
            <td>操作</td>
        </tr>
        </thead>


        <tbody>
        <%--
        for(Car car : cars){
          out.println(car.getName())
        }
        --%>
        <c:forEach var="contact" items="${requestScope.contacts}" varStatus="row">
            <tr>
                <td>${row.index + 1}</td>
                <td>${contact.name}</td>
                <td>${contact.mobil}</td>
                <td>${contact.org}</td>
                <td>${contact.phone}</td>
                <td>${contact.email}</td>
                <td>${contact.address}</td>
                <td>
                    <button type="button" onclick="findById(${contact.id})" class="btn btn-info">修改</button>
                    &nbsp;&nbsp;
                    <button type="button" onclick="removeCar(${contact.id},'${contact.name}')" class="btn btn-danger">删除</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    function findById(id) {
    window.location.href="ContactServlet?action=findById&id="+id
    }

    function removeCar(id,name) {
    if (confirm("是否确认删除 " + name + " ?")) {
        location.href = "ContactServlet?action=remove&id=" + id
        }
    }
</script>
</body>
</html>

