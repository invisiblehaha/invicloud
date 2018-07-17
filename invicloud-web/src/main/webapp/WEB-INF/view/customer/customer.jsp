
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/base.jsp" %>
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
        <li><a href="#"><i class="fa fa-dashboard"></i>客户管理</a></li>
        <li class="active">会员管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content container-fluid">

<table id="table"></table>


<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<!-- Bootstrap table -->
<script src="${ctxsta}/bootstrap-table/dist/bootstrap-table.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
<!-- 自定义js -->
<script>
    baselocation="${ctx}";
    var $table = $('#table');
    $(function () {
        $table.bootstrapTable({
            url: baselocation + '/customer/customer/',
            height: '1000',
            striped: true,
            search: true,
            pageNumber:1,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            pageSize: '20',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'customerId',
            maintainSelected: true,
            sortOrder: 'desc',
            columns: [
                {field: 'customerId', title: '顾客ID',align: 'center',sortable: 'true'},
                {field: 'customerToken', title: '人脸标识', halign: 'center', align: 'center',sortable: 'false'},
                {field: 'realName', title: '真实姓名',halign: 'center',align: 'center',sortable: 'true'},
                {field: 'sex',title: '性别',halign: 'center',align: 'center',sortable: 'true'},
                {field: 'age',title: '年龄',halign: 'center',align: 'center',sortable: 'true'},
                {field: 'picImg',title: '顾客图片',halign: 'center',align: 'center',sortable: 'false'},
                {field: 'status',title: '状态',halign: 'center',align: 'center',sortable: 'true'},
                {field: 'telephone',title: '手机号码',halign: 'center',align: 'center',sortable: 'false'},
                {field: 'regeistTime',title: '注册时间',halign: 'center',align: 'center',formatter: 'timeFormatter',sortable: 'true'},
                {field: 'noble',title: '会员级别',halign: 'center',align: 'center',sortable: 'true'}]
        });
    });


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