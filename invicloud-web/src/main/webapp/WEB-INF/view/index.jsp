
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //{pageContext.request.ContextPath}
    String path = request.getContextPath();
    // 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    // 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。
    // pageContext.setAttribute("basePath", basePath);
%>
<%@ page isELIgnored="false" %>
<%@ include file="/WEB-INF/base.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
<section class="content-header">
    <h1>
        InvisiCloud
        <small>首页</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
    </ol>
</section>

<!-- Main content -->
<section class="content container-fluid">




</section>

</body>
</html>
