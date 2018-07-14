<%@ page import="cn.vision.invicloud.support.entity.Customer" %>
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
    <link rel="stylesheet" href="${ctxsta}/css/css2.css"/>
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
<section class="content container-fluid">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
<div class="container-left">
<div class="quarter1">
<table class="person">
    <tr>
        <td class="lefttd">客户名：</td>
        <td>${customer.realName}</td>
    </tr>
    <tr>
        <td class="lefttd">性别：</td>

        <td>
            <c:if test="${customer.sex eq 0}">保密</c:if>
            <c:if test="${customer.sex eq 1}">男</c:if>
            <c:if test="${customer.sex eq 2}">女</c:if>
        </td>
    </tr>
    <tr>
        <td class="lefttd">喜好列表：</td>
        <td>${likeList}</td>
    </tr>
    <tr>
        <td class="lefttd">平均消费金额：</td>
        <td>${averageMoney}</td>
    </tr>
    <tr>
        <td class="lefttd">平均消费数量：</td>
        <td>${averageNum}</td>
    </tr>
</table>
</div>
    <div class="quarter3">
        <h5>推荐商品：</h5>
<c:forEach items="${goods}" var="good">
    <div class="columns">
        <a href="productDetail.jsp?id=${good.productId}"><p class="thumbnail_align"> <img src="${good.picImg}" class="thumbnail"/> </p></a>
        <small>${good.productName}</small>
    </div>
</c:forEach>
    </div>
</div>
    <div class="container-right">
    <div class="quarter2">
        <nav>商品列表</nav>

    </div>
    <div class="quarter4">
        <button id="createOrder">生成订单</button>
<div class="orderItem">
    <div class="one">
        <img src="#"/>
        <p>商品描述</p>
    </div>
    <div class="two">

    </div>
    <div class="three">

    </div>

</div>
        <div class="orderItem">
            <div class="one">
                <img src="#"/>
                <p>商品描述</p>
            </div>
            <div class="two">

            </div>
            <div class="three">

            </div>

        </div>
    </div>
    </div>
</section>
</body>
</html>