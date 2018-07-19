<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="cn.vision.invicloud.support.pojo.vo.UserVO" %>
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

<head>
    <title>个人信息</title>
    <script type="text/javascript">baselocation="${ctx}"</script>
    <link rel="stylesheet" href="${ctxsta}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/css/css-table.css"/>
</head>
<body style="width: 100%">
<section class="content-header">
<h1>
    InvisiCloud
    <small>系统管理</small>
</h1>
<ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i>系统管理</a></li>
    <li class="active">个人信息</li>
</ol>
</section>

<!-- Main content -->
<section class="container content container-fluid">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
    <form id="updateForm">
    <table class="person">
        <tr>
            <td>&nbsp员工编号：</td>
            <td>
                <input type="text" readonly="readonly" class="inp" value="${user.userId}">
            </td>
        </tr>
        <tr>
            <td>&nbsp员工登录名：</td>
            <td>
                <input id="login" name="login" type="text" readonly="readonly" class="inp" value="${user.loginName}">
            </td>
        </tr>
        <tr>
            <td>&nbsp员工真名：</td>
            <td>
                <input id="real"  name="real" type="text" readonly="readonly"  class="inp" value="${user.userName}">
            </td>
        </tr>
        <tr>
            <td>&nbsp员工角色：</td>
            <td>
                <c:forEach items="${roles}" var="role">
                <input type="text" readonly="readonly" class="inp" value="${role.roleName}">
                </c:forEach>
            </td>
        </tr>
        <tr>
        <tr>
            <td>&nbsp性别：</td>
            <%
                String sex="";
                if(((UserVO)session.getAttribute("user")).getSex()==1){
                    sex="男";
                }else{
                    sex="女";
                }
            %>
            <td>
                <input id="sex" name="sex" readonly="readonly"  type="text" class="inp" value="<%=sex%>">
            </td>
        </tr>
        <tr>
            <td>&nbsp年龄：</td>
            <td>
                <input id="age" name="age" readonly="readonly"  type="text" class="inp" value="${user.age}">
            </td>
        </tr>
        <tr>
        <td>&nbsp员工状态：</td>
        <td>
            <input type="text" readonly="readonly" class="inp" value="${user.status}">
        </td>
        </tr>
        <tr>
            <td>&nbsp联系方式：</td>
            <td>
                <input id="tel" name="tel" readonly="readonly" type="text" class="inp" value="${user.telephone}">
            </td>
        </tr>
        <tr>
            <td>&nbsp创建时间：</td>
            <td>
                <input type="text" readonly="readonly" class="inp" value="${user.createTime}">
            </td>
        </tr>
        <tr>
            <td class="edit"><button id="editInfo" type="button">修改资料</button></td>
            <td class="edit"><button id="sureEdit" type="button">确认修改</button></td>
        </tr>
    </table>
    </form>
</section>
<script type="text/javascript" src="<%=basePath%>static/js/js-table.js"></script>
</body>
</html>
