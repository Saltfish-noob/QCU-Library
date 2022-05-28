<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/3/18
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登陆失败</title>
    <script>
      alert('登陆失败，若账户密码正确请联系系统管理员');
      setTimeout(function(){
        window.location.href="http://localhost:8080/QCU_Library/login.jsp";
      },2)
    </script>
</head>
  <body>

</body>
</html>
