<%--
  Created by IntelliJ IDEA.
  User: Hatto
  Date: 2018/7/9
  Time: 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"  %>
<%@ include file="/WEB-INF/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>日志</title>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
</head>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.slim.js"></script>
<table id="table"></table>
<!-- Bootstrap table -->
<script src="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/extensions/export/bootstrap-table-export.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
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
            showColumns: true,
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
                {field: 'createTime', title: '操作时间', formatter: "timeFormatter", halign: 'center'},
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

    function timeFormatter(value) {
        return new Date(value).Format("yyyy-MM-dd HH:mm:ss");
    }
</script>
</body>
</html>
