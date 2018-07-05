<%--
  Created by IntelliJ IDEA.
  User: HATTORI
  Date: 2018/7/4
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/base.jsp" %>
<html>
<head>
    <title>invicloud登陆页面</title>
</head>

<body>

<html>

<head>
    <title>invicloud登陆页面</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>

<body>
<form action="${ctx}\login" method="post">

    <div>
        <label for="loginName">Enter name: </label>
        <input type="text" name="loginName" id="loginName" required>
    </div>
    <div>
        <label for="loginPassword">Enter password: </label>
        <input type="text" name="loginPassword" id="loginPassword" required>
    </div>
    <div>
        <label for="registerCode">Enter registercode: </label>
        <input type="text" name="registerCode" id="registerCode" required>
        <img src="${ctx}\captcha-image.jpg" alt="registerCode"/>
    </div>
    <div>
        <input type="submit" value="Subscribe!">
    </div>
</form>

</body>

</html>

</body>

</html>
