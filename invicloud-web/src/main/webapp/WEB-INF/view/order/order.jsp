<%--
  Created by IntelliJ IDEA.
  User: 32072
  Date: 2018/7/9
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
</head>
<body>



<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<table id="table"></table>
<!-- Bootstrap table -->
<script src="${ctxsta}/bootstrap-table/dist/bootstrap-table.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/extensions/export/bootstrap-table-export.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>

<script>
    baselocation="${ctx}";
    var $table = $('#table');
    $(function () {
        $table.bootstrapTable({
            url: baselocation + '/order/order/',
            height: '650',
            search: true,
            striped: true,
            minimumCountColumns: 2,
            showRefresh: true,
//            showColumns: true,
            uniqueId: "orderId",
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            pageSize: 20,
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'orderId',
            maintainSelected: true,
            sortName: 'orderId',
            sortOrder: 'desc',
            columns: [{
                field: 'orderId',
                title: '订单编号',
                halign: 'center',
                align: 'center',
                sortable: 'true'
            }, {
                field: 'customerId',
                title: '客户编号',
                halign: 'center',
                align: 'center',
                sortable: 'true'
            }, {
                field: 'payType',
                title: '付款类型',
                halign: 'center',
                align: 'center',
                sortable: 'false'
            }, {
                field: 'buyAmount',
                title: '购买数量',
                halign: 'center',
                align: 'center',
                sortable: 'true'
            }, {
                field: 'payAmount',
                title: '购买金额',
                halign: 'center',
                align: 'center',
                sortable: 'true'
            }
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

</script>
</body>
</html>