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
    <title>客户详情</title>
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
        <img src="${product.picImg}" class="showImg">

        <table class="bottom-detail">
            <tr>
                <td class="lefttd">商品ID：</td>
                <td>${product.productId}</td>
            <tr>
                <td class="lefttd">输入数量：</td>
                <td><input type="text" name="buyAmount" value="1" class="buyNum"/></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button id="addToOrder">加入订单</button>
                </td>

            </tr>
        </table>
    </div>
    <div class="container-right">

        <table class="right-detail">
            <tr>
                <td class="lefttd">商品名称：</td>
                <td>${product.productName}</td>
            </tr>
            <tr>
                <td class="lefttd">商品价格</td>

                <td>
                    ${product.productPrice}
                </td>
            </tr>
            <tr>
                <td class="lefttd">剩余数量：</td>
                <td>${product.stock}</td>
            </tr>
            <tr>
                <td class="lefttd">商品介绍：</td>
                <td>${product.productIntroduce}</td>
            </tr>
            <tr>
                <td class="lefttd">商品描述：</td>
                <td>${product.remarks}</td>
            </tr>
        </table>
        </div>

</section>
</body>
</html>