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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"/>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <style type="text/css">
        body{
            width: 100%;
            height: 100%;
        }
        .main{
            width:100%;
            margin-top: 10%;
        }
        #theImage{
            width: 100%;
        }
        .theButton{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            flex-direction: row;
            justify-content: center;
            align-items: center;

        }
        .image{
            width: 80%;
            margin-bottom: 20%;
            margin-right: auto;
            margin-left: auto;
            margin-top: 10%;
        }

        .button{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            margin-bottom: 10%;
            color: white;

        }
        .name{

            margin-top: 20%;
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            margin-bottom: 10%;
        }
        .description{

            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            margin-bottom: 10%;
        }
        .price{
            /*width: 100%;*/
            /*height:22%;*/
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            margin-bottom: 10%;
        }
        .remain{
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            margin-bottom: 20%;
        }
        #nowPrice{
            color:#408eba;
            font-size: 30px;
        }


    </style>
    <script>
        function back() {
            window.location.href="/store/shopping/shopping/view";
        }
    </script>
</head>

<body>
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
    <div class="container main">
        <div class="row" style="align-content: center;text-align: center;justify-content: center;height: 90%">
            <div class="col-md-5 col-md-offset-1" style="opacity: 0.7;background-color: white;height: 80%;border-radius: 5px;">
                <div class="row">
                    <div class="image">
                        <img id="theImage" src="${product.picImg}"/>
                    </div>
                    <div class="leftFoot">
                        <div class="theButton">
                            <div class="button"><button style="background-color: #408eba; border-radius: 5px;" type="button" onclick="back()">返回</button></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-md-offset-1" style="opacity: 0.7;background-color: white;height: 80%;border-radius: 5px;">
                <div class="name">
                    <div><h4>${product.productName}</h4></div>
                </div>
                <div class="description">
                    <div><p>${product.productIntroduce}</p></div>
                </div>
                <div class="description">
                    <div><p>${product.remarks}</p></div>
                </div>
                <div class="remain">
                    <div class="left">剩余数量：</div><div id="nowRemain">${product.stock}</div>
                </div>
                <div class="price">
                    <div class="left">价格：</div><div id="nowPrice">￥${product.productPrice}</div>
                </div>

            </div>
        </div>
    </div>

</section>
</body>
</html>