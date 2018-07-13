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
    <link rel="stylesheet" type="text/css" href="${ctxsta}/css/browse-2.css" />
</head>

<body>
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
    <div id="show">

        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/head-1.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomer_id</div>
                        <div class="row" style="color: #6c757d;font-size: small">10</div>
                    </div>
                </div>
                <div class="sharing">
                </div>

            </div>
            <input type="hidden" name="customerId" value="10" class="inputSubmit">
        </div>

        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/head-2.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomer_id</div>
                        <div class="row" style="color: #6c757d;font-size: small">8</div>
                    </div>
                </div>

            </div>
            <input type="hidden" name="customerId" value="8" class="inputSubmit">
        </div>
        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/head-3.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomer_id</div>
                        <div class="row" style="color: #6c757d;font-size: small">5</div>
                    </div>
                </div>
                <div class="sharing">
                    <p>到来时间：2018 July 13 8:31 am</p>
                </div>
            </div>
            <input type="hidden" name="customerId" value="5" class="inputSubmit">
        </div>
        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/head-4.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomer_id</div>
                        <div class="row" style="color: #6c757d;font-size: small">502</div>
                    </div>
                </div>
                <div class="sharing">
                    <p>到来时间：2018 July 13 8:31 am</p>
                </div>
            </div>
            <input type="hidden" name="customerId" value="502" class="inputSubmit">
        </div>
        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/head-5.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomer_id</div>
                        <div class="row" style="color: #6c757d;font-size: small">503</div>
                    </div>
                </div>
                <div class="sharing">
                    <p>到来时间：2018 July 13 8:31 am</p>
                </div>
            </div>
            <input type="hidden" name="customerId" value="503" class="inputSubmit">
        </div>
        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/head-6.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomer_id</div>
                        <div class="row" style="color: #6c757d;font-size: small">504</div>
                    </div>
                </div>
                <div class="sharing">
                    <p>到来时间：2018 July 13 8:31 am</p>
                </div>
            </div>
            <input type="hidden" name="customerId" value="504" class="inputSubmit">
        </div>
        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/head-7.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomer_id</div>
                        <div class="row" style="color: #6c757d;font-size: small">505</div>
                    </div>
                </div>
                <div class="sharing">
                    <p>到来时间：2018 July 13 8:31 am</p>
                </div>
            </div>
            <input type="hidden" name="customerId" value="505" class="inputSubmit">
        </div>

        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/head-8.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomer_id</div>
                        <div class="row" style="color: #6c757d;font-size: small">501</div>
                    </div>
                </div>
                <div class="sharing">
                    <p>到来时间：2018 July 13 8:31 am</p>
                </div>
            </div>
            <input type="hidden" name="customerId" value="501" class="inputSubmit">
        </div>












    </div>

</section>
<script src="${ctxsta}/js/modalEffects.js"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>