<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>H-ui</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="static/h-ui/css/H-ui.min.css">
    <link rel="stylesheet" href="static/h-ui.admin/css/H-ui.login.css">
    <link rel="stylesheet" href="static/Hui-iconfont/1.0.8/iconfont.css">
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
<div id="loginForm" class="loginBox">
    <form class="form form-horizontal" id="f" method="post" name="f">
        <div class="row cl">
            <label for="username" class="form-label col-xs-3">
                <i class="Hui-iconfont">&#xe60d;</i>
            </label>
        <div class="formControls col-xs-8">
            <input type="text" name="username" id="username" placeholder="用户名" class="input-text size-L">
        </div>
        </div>

        <div class="row cl">
            <label for="password" class="form-label col-xs-3">
                <i class="Hui-iconfont">&#xe60e;</i>
            </label>
            <div class="formControls col-xs-8">
                <input type="text" name="password" id="password" placeholder="用户名" class="input-text size-L">
            </div>
        </div>

        <div class="row cl">
            <div class="formControls col-xs-8 col-xs-offset-3">
                <input type="button" id="btnLogin" class="btn btn-primary radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                <input type="reset" class="btn btn-default radius size-L" value="&nbsp;重&nbsp;&nbsp;&nbsp;&nbsp;置&nbsp;">
            </div>
        </div>
    </form>
</div>
</div>

<div class="footer">
    Copyright &copy;2018-2028 Wang.net All Rights Reserved
</div>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="static/h-ui/js/H-ui.min.js"></script>
<script src="static/layer/2.4/layer.js"></script>
<script>
    $(window).on("load",()=>{
        if(window!=top){
            top.location.href="index.jsp"
        }
        $("#btnLogin").click(()=>{
            var username=$.trim($("#username").val())
            var password=$.trim($("#password").val())
            if(!username){
                //layer弹窗
                layer.alert("请输入您的用户名")
                return
            }
            if(!password){
                //layer弹窗
                layer.alert("请输入您的密码")
                return
            }
            $.ajax({
                type:"post",
                url:"LoginServlet",
                data:{username:username,password:password},
                dataType:"json",
                success:function(user){
                    console.log(user,'.......')
                    if(user&&user.id){
                        localStorage.setItem("username",user.name)
                        localStorage.setItem("createDate",user.createDate)
                        location.href="main.html"
                    }else{
                        layer.alter(user.message)
                    }
                }
            })
        })
    })
</script>
</body>
</html>
