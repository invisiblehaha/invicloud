<%@ page language="java" contentType="text/html; charset=utf-8" %>
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
    <title>个人信息</title>
    <script type="text/javascript">baselocation="${ctx}"</script>
    <link rel="stylesheet" href="${ctxsta}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
</head>
<body style="width: 100%">
<section class="content-header">
    <h1>
        InvisiCloud
        <small>系统管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>系统管理</a></li>
        <li class="active">分析数据-喜好（基于历史）</li>
    </ol>
</section>

<!-- Main content -->
<section class="container content container-fluid">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>

    <table id="table" class="table-responsive"></table>

    <script src="<%=basePath%>static/bootstrap-table/dist/bootstrap-table.js"></script>
    <script src="<%=basePath%>static/bootstrap-table/dist/extensions/export/bootstrap-table-export.js"></script>
    <script src="<%=basePath%>static/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
    <script src="<%=basePath%>static/bootstrap/js/bootstrap.js"></script>

    <script>
        baselocation="${ctx}";
        var $table = $('#table');
        $(function () {
            $table.bootstrapTable({
                url: baselocation + '/system/analy2/',
                method: 'post',
                height: '650',
                striped: true,
                //cache: false,
                sortName:'callBackRate',
                sortable: true, //排序方式
                sortOrder: "desc",//初始化加载第一页，默认第一页
                pageNumber: 1,//这默认页码
                pageSize: 15,
                pageList: [10, 15, 25, 50, 100],
                //  search: true,
                showRefresh: true,
                minimumCountColumns: 2,
                clickToSelect: true,
                detailView: true,
                detailFormatter: 'detailFormatter',
                pagination: true,
                paginationLoop: false,
                sidePagination:'client',

                silentSort: false,
                smartDisplay: false,
                escape: true,
                searchOnEnterKey: true,
                idField: 'customerId',
                maintainSelected: true,

                columns: [
                    {field: 'customerId', title: '客户编号', sortable: true, align: 'center', halign: 'center'},
                    {field: 'likeList', title: '推荐商品ID列表', align: 'center', halign: 'center'},
                ],
                queryParams: function (params) {
                    return {
                        inDateFrom: $('#start').val(),
                        inDateTo: $('#end').val(),
                        type: $("#viewType").attr("viewType")
                    };
                },
                responseHandler: function(data){
                    return data.rows;
                }
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
