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
                    <a href="<%=basePath%>recommend/product/view?id=${good.productId}&customerId=${customer.customerId}"><p class="thumbnail_align"> <img src="${good.picImg}" class="thumbnail"/> </p></a>
                    <small>${good.productName}</small>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="container-right" >
        <div id="container1" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
        <%--添加饼图--%>
        <div id="container2" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
    </div>
</section>

<script>
    Highcharts.chart('container1', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '会员消费分布'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: [
                <c:forEach var="catitem" items="${catmap}" >
                {
                    name: <c:out value="${catitem.name}" />,
                    y: <c:out value="${catitem.percentage}" />
                },
                </c:forEach>
            ]
        }]
    });
    Highcharts.chart('container2', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: '消费心情'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: 'Brands',
            colorByPoint: true,
            data: [
                <c:forEach var="catitem" items="${emap}" >
                {
                    name: <c:out value="${catitem.name}" />,
                    y: <c:out value="${catitem.percentage}" />
                },
                </c:forEach>
            ]
        }]
    });
</script>


</body>
</html>