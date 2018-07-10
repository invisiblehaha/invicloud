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
    <link rel="stylesheet" href="${ctxsta}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
</head>

<body>
  
<table id="table"></table>

<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="${ctxsta}/bootstrap/js/popper.js"></script>
<!-- Bootstrap table -->
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/bootstrap-table.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
<!-- 自定义js -->
<script>
    baselocation="${ctx}";
    var $table = $('#table');
    $(function () {
        $table.bootstrapTable({
            url: baselocation + '/system/log/',
            height: '650',
            striped: true,
            search: true,
            showRefresh: true,
     //       showColumns: true,
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
            idField: 'logId',
            maintainSelected: true,
            sortName: 'logId',
            sortOrder: 'desc',
            columns: [
                {field: 'logId', title: '编号', sortable: true, align: 'center', halign: 'center'},
                {field: 'userId', title: '操作用户', align: 'center', halign: 'center'},
                {field: 'createTime', title: '操作时间', align: 'center', halign: 'center'},
                {field: 'spendTime', title: '耗时', align: 'center', sortable: true, halign: 'center'},
                {field: 'url', title: '请求路径', halign: 'center'},
                {field: 'method', title: '请求类型', align: 'center', halign: 'center'},
                {field: 'userAgent', title: '用户标识', halign: 'center'},
                {field: 'userIp', title: 'IP地址', align: 'center', halign: 'center'}
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
