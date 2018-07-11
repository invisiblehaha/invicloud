
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

<table id="table"
       data-toggle="table"
       <%--data-height="1000"--%>
       data-search="true"
       data-show-refresh="true"
       <%--data-show-toggle="true"--%>
       <%--data-show-export="true"--%>
       <%--data-show-pagination-switch="true"--%>

       data-detail-view="true"
       data-detail-formatter="detailFormatter"
       data-url="${ctx}/customer/customer/"
       data-page-size="10"
       data-page-list="[20, 50, 100, 200]"
       data-side-pagination="server"
       data-striped="true"
       data-pagination="true"
       data-sort-order="desc">
    <thead>
    <tr>
        <th data-field="customerId" data-halign="center" data-align="center"
            data-sortable="true">顾客
        </th>
        <th data-field="customerToken" data-haligIDn="center" data-align="center"
            data-sortable="true">人脸标识
        </th>
        <th data-field="realName" data-halign="center" data-align="center"
            data-sortable="true">真实姓名
        </th>
        <th data-field="sex" data-halign="center" data-align="center"
            data-sortable="true">性别
        </th>
        <th data-field="age" data-halign="center" data-align="center"
            data-sortable="true">年龄
        </th>
        <th data-field="picImg" data-halign="center" data-align="center"
            data-sortable="true">顾客图片
        </th>
        <th data-field="status" data-halign="center" data-align="center"
            data-sortable="true">状态
        </th>
        <th data-field="telephone" data-halign="center" data-align="center"
            data-sortable="true">手机号码
        </th>
        <th data-field="regeistTime" data-halign="center" data-align="center"
            data-sortable="true">注册时间
        </th>
        <th data-field="noble" data-halign="center" data-align="center"
            data-sortable="false">会员级别
        </th>
    </tr>
    </thead>
</table>


<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<!-- Bootstrap table -->
<script src="${ctxsta}/bootstrap-table/dist/bootstrap-table.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/extensions/export/bootstrap-table-export.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
<!-- 自定义js -->
<script>
    baselocation=${ctx};
    InitSubTable = function (index, row, $detail) {

        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            url: baselocation + '/customer/customer/',
            method: 'get',
            sidePagination: 'server',
            clickToSelect: true,
            uniqueId: "customerId",
            pagination: true,

            pageNumber: 1,
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
            },],
        });
    };

</script>
</section>
</body>
</html>