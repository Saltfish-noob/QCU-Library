<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Clear login Form Responsive Widget Template :: w3layouts</title>
    <!-- Meta-Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta name="keywords"
          content="Clear login Form a Responsive Web Template, Bootstrap Web Templates, Flat Web Templates, Android Compatible Web Template, Smartphone Compatible Web Template, Free Webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- //Meta-Tags -->
    <!-- Stylesheets -->
    <link href="${pageContext.request.contextPath}/css/login-font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/login-style.css" rel='stylesheet' type='text/css'/>
    <!--// Stylesheets -->
    <!--fonts-->
    <!-- title -->
    <!-- body -->
    <link href="${pageContext.request.contextPath}/css/login-google.css" rel="stylesheet" type="text/css"/>
    <!--//fonts-->
</head>

<body>
<h1>欢迎使用图书管理系统</h1>
<div class="w3ls-login box box--big">
    <!-- form starts here -->
    <form action="${pageContext.request.contextPath}/login.do" method="post">
        <div class="agile-field-txt">
            <label>
                <i class="fa fa-user" aria-hidden="true"></i> 账户名: </label>
            <input type="text" name="username" placeholder="请输入账户名 " required=""/>
        </div>
        <div class="agile-field-txt">
            <label>
                <i class="fa fa-envelope" aria-hidden="true"></i> 密 码: </label>
            <input type="password" name="password" placeholder="请输入密码 " required="" id="myInput"/>
            <div class="agile_label">
                <input id="check3" name="check3" type="checkbox" value="show password" onclick="myFunction()">
                <label class="check" for="check3">显示密码</label>
            </div>
        </div>
        <!-- script for show password -->
        <script>
            function myFunction() {
                var x = document.getElementById("myInput");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
            }
        </script>
        <!-- //script ends here -->
        <div class="w3ls-bot">
            <div class="switch-agileits">
                <label class="switch">
                    <input type="checkbox" id="remember-me" name="remember-me">
                    <span class="slider round"></span>
                    七天内免登录&emsp;&emsp;&emsp;
                </label>
            </div>
            <div class="form-end">
                <input type="submit" value="LOGIN">
            </div>
            <div class="clearfix"></div>
        </div>
    </form>
</div>
<!-- //form ends here -->
<!--copyright-->
<!--//copyright-->
</body>

</html>