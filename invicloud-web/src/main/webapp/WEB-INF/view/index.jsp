
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

</head>
<body>
<a href="<%=basePath%>product/category/view">商品分类</a>
<a href="<%=basePath%>product/product/view">商品</a>
<a href="<%=basePath%>system/log/view">日志</a>
<a href="<%=basePath%>order/order/view">订单</a>
<a href="<%=basePath%>customer/customer/view">顾客</a>
</body>
</html>
