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
<c:if test="${empty sessionScope.username}">
    <c:redirect url="index.jsp"/>
</c:if>
<div class="container">
    <h1>基于 MVC 的 Java Web 开发</h1>
    <h5>
        Hi,${sessionScope.username},Welcome Back！
        &nbsp;&nbsp;
        <a href="LoginServlet?action=logout">注销</a>
    </h5>
    <div class="form-group row">
        <form action="CarServlet" method="post" name="f">
            <input type="hidden" name="action" value="list">
            <input type="text" name="name" placeholder="车名" class="form-control">
            <input type="number" step="0.0001" name="price" placeholder="价格"  class="form-control">
            <button type="submit" class="btn btn-primary">查询</button>
            &nbsp;&nbsp;
            <a href="caredit.jsp">新增</a>
        </form>
    </div>

    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <td>No.</td>
            <td>车名</td>
            <td>价格</td>
            <td>生产日期</td>
            <td>操作</td>
        </tr>
        </thead>

        <tfoot>
        <tr>
            <td>No.</td>
            <td>车名</td>
            <td>价格</td>
            <td>生产日期</td>
            <td>操作</td>
        </tr>
        </tfoot>

        <tbody>
        <%--
        for(Car car : cars){
          out.println(car.getName())
        }
        --%>
        <c:forEach var="car" items="${requestScope.cars}" varStatus="row">
            <tr>
                <td>${row.index + 1}</td>
                <td>${car.name}</td>
                <td>
                    <fmt:formatNumber value="${car.price}" pattern="#########.0000"/>
                </td>
                <td>
                        ${car.createDate}
                        <%--
                        <fmt:formatDate value="${car.createDate}" pattern="yyyy-MM-dd"/>
                        --%>
                </td>
                <td>
                    <button type="button" onclick="findById(${car.id})" class="btn btn-info">修改</button>
                     &nbsp;&nbsp;
                    <button type="button" onclick="removeCar(${car.id},'${car.name}')" class="btn btn-danger">删除</button>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5">
                <select id="pagesize">
                    <option value="5">5</option>
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="50">50</option>
                </select>&nbsp;&nbsp;
                <a href="CarServlet?pageno=1&pagesize=5&sort=id&order=asc">首页</a>
                &nbsp;&nbsp;
                <a href="CarServlet?pageno=${para.pageno-1}&pagesize=5&sort=id&order=asc">上一页</a>
                &nbsp;&nbsp;
                <a href="CarServlet?pageno=${para.pageno-1}&pagesize=5&sort=id&order=asc">下一页</a>
                &nbsp;&nbsp;
                <a href="CarServlet?pageno=1&pagesize=5&sort=id&order=asc">尾页</a>
                &nbsp;&nbsp;
                第${pageno}页，共页
            </td>
        </tr>
        </tbody>
    </table>
</div>
<script>
    function findById(id) {
        window.location.href="CarServlet?action=findById&id="+id
    }

    function removeCar(id,name) {
        if (confirm("是否确认删除 " + name + " ?")) {
            location.href = "CarServlet?action=remove&id=" + id
        }
    }
</script>
</body>
</html>
