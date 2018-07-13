<%@ page contentType="text/html; charset=utf-8" language="java"  %>
<%@ include file="/WEB-INF/base.jsp" %>
<%
    //{pageContext.request.ContextPath}
    String path = request.getContextPath();
    // 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    // 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。
    // pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    <title>日志</title>
    <script type="text/javascript">baselocation="${ctx}"</script>
    <link rel="stylesheet" href="${ctxsta}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/css/css-table.css"/>
</head>

<body style="width: 100%">
<section class="content-header">
    <h1>
        InvisiCloud
        <small>推荐系统</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>推荐系统</a></li>
        <li class="active">${customer.getCustomerId()}</li>
    </ol>
</section>
<!-- Main content -->
<section class="container content container-fluid">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
<table class="person">
    <tr>
        <td></td>
        <td>${customer.getRealName()}</td>
    </tr>
    <tr>
        <td></td>
        <td>${customer.getSex()}</td>
    </tr>
    <tr>
        <td></td>
        <td>${likeList}</td>
    </tr>
    <tr>
        <td></td>
        <td>${averageMoney}</td>
    </tr>
    <tr>
        <td></td>
        <td>${averageNum}</td>
    </tr>
</table>



</section>
</body>
</html>