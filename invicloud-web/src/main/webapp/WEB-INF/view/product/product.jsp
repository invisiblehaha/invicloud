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
    <title>所有商品</title>
    <link rel="stylesheet" href="${ctxsta}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
</head>

<body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>

<table id="table"></table>

<script src="<%=basePath%>static/bootstrap-table/dist/bootstrap-table.js"></script>
<script src="<%=basePath%>static/bootstrap-table/dist/extensions/export/bootstrap-table-export.js"></script>
<script src="<%=basePath%>static/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
<script src="<%=basePath%>static/bootstrap/js/bootstrap.js"></script>

<script>
    baselocation="${ctx}";
    var $table = $('#table');
    $(function () {
        $table.bootstrapTable({
            url: baselocation + '/product/product/',
            height: '650',
            striped: true,
            search: true,
            showRefresh: true,
            //showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            pageSize: '20',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'productId',
            maintainSelected: true,
            sortName: 'productId',
            sortOrder: 'desc',
            columns: [
                {field: 'productId', title: '编号', sortable: true, align: 'center', halign: 'center'},
                {field: 'productName', title: '商品名称', align: 'center', halign: 'center'},
                {field: 'productPrice', title: '价格', align: 'center', halign: 'center'},
//                {field: 'productIntroduce', title: '商品介绍', align: 'center',  halign: 'center'},
                {field: 'stock', title: '库存', align: 'center',  halign: 'center'},
//                {field: 'remarks', title: '备注', align: 'center', halign: 'center'},
            ]
        });
    });

    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }


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
</body>
</html>
