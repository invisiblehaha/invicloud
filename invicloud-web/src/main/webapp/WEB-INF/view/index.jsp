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
<%@ include file="/WEB-INF/base.jsp" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
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
            alert(openEvt.Data);
        }

        function onMessage(evt) {
            alert(evt.data);
        }

        function onError() {
        }

        function onClose() {
        }

        function doSendUsers() {
            if (websocket.readyState === websocket.OPEN) {
                var msg = document.getElementById("inputMsg").value;
                websocket.send(msg);//调用后台handleTextMessage方法
                alert("发送成功!");
            } else {
                alert("连接失败!");
            }
        }

        window.close = function () {
            websocket.onclose();
        }

    </script>

</head>
<body>
<a href="<%=basePath%>product/category/view">商品分类</a>
<a href="<%=basePath%>product/product/view">商品</a>
<a href="<%=basePath%>system/log/view">日志</a>
<a href="<%=basePath%>order/order/view">订单</a>
<a href="<%=basePath%>customer/customer/view">顾客</a>
</body>
</html>
