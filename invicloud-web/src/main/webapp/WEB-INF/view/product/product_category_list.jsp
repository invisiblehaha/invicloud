<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
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
<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="${ctxsta}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
</head>

<body>
<section class="content-header">
    <h1>
        InvisiCloud
        <small>库存管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>库存管理</a></li>
        <li class="active">分类管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
<table id="table"
       data-toggle="table"
       data-height="600"
       data-search="true"
       data-show-refresh="true"
       <%--data-show-toggle="true"--%>
       <%--data-show-pagination-switch="true"--%>
       <%--data-show-columns="true"--%>
       data-detail-view="true"
       data-detail-formatter="detailFormatter"
       data-url="${ctx}/product/category/gid/0"
       data-page-size="20"
       data-page-list="[20, 50, 100, 200]"
       data-side-pagination="server"
       <%--data-striped="true"--%>
       data-pagination="true"
       data-sort-order="desc">
    <thead>
    <tr>
        <th data-field="categoryId" data-halign="center" data-align="center"
            data-sortable="true">分类编号
        </th>
        <th data-field="categoryName" data-halign="center" data-align="center"
            data-sortable="true">分类名称
        </th>
        <th data-field="remarks" data-halign="center" data-align="center"
            data-sortable="true">备注
        </th>
        <th data-field="updateTime" data-formatter="timeFormatter" data-halign="center" data-align="center"
            data-sortable="true">修改时间
        </th>
    </tr>
    </thead>
</table>


<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>


<script src="${ctxsta}/bootstrap/js/popper.js"></script>
<!-- Bootstrap table -->
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/bootstrap-table.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>

<!-- 自定义js -->
<script>
    baselocation='${ctx}';
    function detailFormatter(index, row, $detail) {
        InitSubTable(index, row, $detail);
    }
    InitSubTable = function (index, row, $detail) {
        var parentid = row.categoryId;
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            url: baselocation + '/product/category/gid/' + parentid,
            method: 'get',
            sidePagination: 'server',
           // clickToSelect: true,
            uniqueId: "categoryId",
            pagination: true,
            smartDisplay: false,
            paginationLoop: false,
            pageSize: 10,
            pageList: [10, 25],
            pageNumber: 1,
            columns: [{
                field: 'categoryId',
                title: '分类编号',
                halign: 'center',
                align: 'center',
                sortable: 'true'
            }, {
                field: 'categoryName',
                title: '分类名称',
                halign: 'center',
                align: 'center',
                sortable: 'true'
            }, {
                field: 'remarks',
                title: '备注',
                halign: 'center',
                align: 'center',
                sortable: 'true'
            }, {
                field: 'updateTime',
                title: '更新时间',
                halign: 'center',
                align: 'center',
                sortable: 'true',
                formatter: 'timeFormatter'
            },],
            onExpandRow: function (index, row, $Subdetail) {
                oInit.InitSubTable(index, row, $Subdetail);
            }
        });
    };

    function timeFormatter(stamp) {
        var date = new Date(stamp);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        var h = date.getHours();
        var mi = date.getMinutes();
        m = m > 9 ? m : '0' + m;
        return y + '-' + m + '-' + d + ' ' + h + ':' + mi;
    }
</script>
</section>
</body>
</html>