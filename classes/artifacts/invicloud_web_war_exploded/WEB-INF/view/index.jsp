<%@ page contentType="text/html; charset=utf-8" language="java"  %>
<%@ include file="/WEB-INF/base.jsp" %>
<%
    //{pageContext.request.ContextPath}
    String path = request.getContextPath();
    // 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量
    String basePath = request.getServerName() + ":" + request.getServerPort()
            + path ;
    // 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。
    // pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript">baselocation="${ctx}"</script>
    <link rel="stylesheet" href="${ctxsta}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxsta}/css/browse-2.css" />
    <script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.js"></script>
    <script type="text/javascript">
        var heartflag=false;
        var websocket = null;
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://<%=basePath%>/webSocketServer");
        }
        else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://<%=basePath%>/webSocketServer");
        }
        else {
            websocket = new SockJS("http://<%=basePath%>/sockjs/webSocketServer");
        }

        websocket.onopen = onOpen;
        websocket.onmessage = onMessage;
        websocket.onerror = onError;
        websocket.onclose = onClose;

        function onOpen(openEvt) {
        }

        function onMessage(evt) {
            var type=evt.data.substr(0,1);
            var customer=evt.data.substr(2);
            if(type=='1'){
//                var customerdom=$("<div id='"+customer+"'></div>").text(customer);
                var customerdom=$("<div class=\"md-trigger\" data-modal=\"modal-1\" id=\""+customer+"\">" +
                    "<div class=\"container-small\"> " +
                "<div class=\"row\"> " +
                    "<div class=\"col-sm-4\"><img src=\"/static/images/recommend/"+customer+".jpg\" class=\"head\"> " +
                "</div> " +
                "<div class=\"col-sm-8\"> " +
                "<div class=\"row\" style=\"color: #000;font-size: small;margin-top: 5px;\">cumtomer_id</div> " +
                "<div class=\"row\" style=\"color: #6c757d;font-size: small\">"+customer+"</div>" +
                "</div> " +
                "</div> " +
                "<div class=\"sharing\"> " +
                "</div> " +
                "</div> " +
                "<input type=\"hidden\" name=\"customerId\" value=\""+customer+"\" class=\"inputSubmit\"> " +
                "</div>");
                customerdom.click(function(){
                    if (websocket.readyState === websocket.OPEN) {
                        websocket.send("0 "+customer);
                    }
                    window.location.href = baselocation + '/recommend/demo/view'+'?customerId='+customer;
                });


                $("#show").append(customerdom);
            }
            else if(type=='0'){
                $("#"+customer).remove();
            }
            else alert("error:no such operation");
        }

        function onError() {
        }

        function onClose() {
        }

        window.close = function () {
            websocket.onclose();
        }

    </script>
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
                    <div class="col-sm-4"><img src="/static/images/recommend/10.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomerId</div>
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
                    <div class="col-sm-4"><img src="/static/images/recommend/8.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomerId</div>
                        <div class="row" style="color: #6c757d;font-size: small">8</div>
                    </div>
                </div>

            </div>
            <input type="hidden" name="customerId" value="8" class="inputSubmit">
        </div>
        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/5.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomerId</div>
                        <div class="row" style="color: #6c757d;font-size: small">5</div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="customerId" value="5" class="inputSubmit">
        </div>

        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/502.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomerId</div>
                        <div class="row" style="color: #6c757d;font-size: small">502</div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="customerId" value="502" class="inputSubmit">
        </div>

        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/503.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomerId</div>
                        <div class="row" style="color: #6c757d;font-size: small">503</div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="customerId" value="503" class="inputSubmit">
        </div>
        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/504.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomerId</div>
                        <div class="row" style="color: #6c757d;font-size: small">504</div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="customerId" value="504" class="inputSubmit">
        </div>
        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/505.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomerId</div>
                        <div class="row" style="color: #6c757d;font-size: small">505</div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="customerId" value="505" class="inputSubmit">
        </div>

        <div class="md-trigger" data-modal="modal-1">
            <div class="container-small">
                <div class="row">
                    <div class="col-sm-4"><img src="/static/images/recommend/501.jpg" class="head">
                    </div>
                    <div class="col-sm-8">
                        <div class="row" style="color: #000;font-size: small;margin-top: 5px;">cumtomerId</div>
                        <div class="row" style="color: #6c757d;font-size: small">501</div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="customerId" value="501" class="inputSubmit">
        </div>












    </div>
    <div id="userlist"></div>
</section>
<script src="${ctxsta}/js/modalEffects.js"></script>
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>