<%--
  Created by IntelliJ IDEA.
  User: 贺俞凯
  Date: 2018/7/9
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"  %>
<%@ include file="/WEB-INF/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>产品</title>
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
            url: baselocation + '/product/product/',
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
            idField: 'productId',
            maintainSelected: true,
            sortName: 'productName',
            sortOrder: 'desc',
            columns: [
                {field: 'productId', title: '产品编号', sortable: true, align: 'center', halign: 'center'},
                {field: 'productName', title: '产品名称', align: 'center', halign: 'center'},
                {field: 'productPrice', title: '产品价格', sortable: true, align: 'center', halign: 'center'},
                {field: 'productIntroduce', title: '产品介绍', align: 'center', sortable: false, halign: 'center'},
                {field: 'stock', title: '库存', align: 'center',halign: 'center',sortable: true},
                {field: 'picImg', title: '展示图片', align: 'center', halign: 'center'},
                {field: 'remark', title: '备注', halign: 'center',align: 'center'},
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

    /*function timeFormatter(value) {
        return new Date(value).Format("yyyy-MM-dd HH:mm:ss");
    }*/
</script>
</body>
</html>
