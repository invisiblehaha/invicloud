
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/base.jsp" %>
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
        <small>客户管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard">客户管理</i></a></li>
        <li class="active">会员管理</li>
    </ol>
</section>
<!-- Main content -->
<section class="content container-fluid">
    <table id="table"></table>

    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>

    <script src="${ctxsta}/bootstrap/js/popper.js"></script>
    <!-- Bootstrap table -->
    <script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
    <script src="${ctxsta}/bootstrap-table/dist/bootstrap-table.js"></script>
    <script src="${ctxsta}/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>

    <script>
        baselocation="${ctx}";
        var $table = $('#table');
        $(function () {
            $table.bootstrapTable({
                url: baselocation + '/customer/customer/',
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
                pageSize: '10',
                silentSort: false,
                smartDisplay: false,
                escape: true,
                searchOnEnterKey: true,
                idField: 'customerId',
                maintainSelected: true,
                sortName: 'customerId',
                sortOrder: 'desc',
                columns: [{
                    field: 'customerId',
                    title: '顾客ID',
                    halign: 'center',
                    align: 'center',
                    sortable: 'true'
                }, {
                    field: 'customerToken',
                    title: '人脸标识',
                    halign: 'center',
                    align: 'center',
                    sortable: 'false'
                }, {
                    field: 'realName',
                    title: '真实姓名',
                    halign: 'center',
                    align: 'center',
                    sortable: 'true'
                }, {
                    field: 'sex',
                    title: '性别',
                    halign: 'center',
                    align: 'center',
                    sortable: 'true'
                }, {
                    field: 'age',
                    title: '年龄',
                    halign: 'center',
                    align: 'center',
                    sortable: 'true'
                },{
                    field: 'picImg',
                    title: '顾客图片',
                    halign: 'center',
                    align: 'center',
                    sortable: 'false'
                },{
                    field: 'status',
                    title: '状态',
                    halign: 'center',
                    align: 'center',
                    sortable: 'true'
                },{
                    field: 'telephone',
                    title: '手机号码',
                    halign: 'center',
                    align: 'center',
                    sortable: 'false'
                },{
                    field: 'regeistTime',
                    title: '注册时间',
                    halign: 'center',
                    align: 'center',
                    sortable: 'true'
                },{
                    field: 'noble',
                    title: '会员级别',
                    halign: 'center',
                    align: 'center',
                    sortable: 'true'
                },]
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
</section>
</body>
</html>