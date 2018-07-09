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
    <link rel="stylesheet" href="<%=basePath%>static/bootstrap-table/dist/bootstrap-table.min.css"/>
</head>

<body>
<table id="table"
       data-toggle="table"
       data-height="600"
       data-search="true"
       data-show-refresh="true"
       data-show-toggle="true"
       <%--data-show-export="true"--%>
       data-show-pagination-switch="true"
       data-show-columns="true"
       data-detail-view="true"
       data-detail-formatter="detailFormatter"
       data-url="${ctx}/product/category/gid/1"
       data-page-size="20"
       data-page-list="[20, 50, 100, 200]"
       data-side-pagination="server"
       data-striped="true"
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
        <th data-field="updateTime" data-halign="center" data-align="center"
            data-sortable="false">更新时间
        </th>
    </tr>
    </thead>
</table>


<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<!-- Bootstrap table -->
<script src="<%=basePath%>static/bootstrap-table/dist/bootstrap-table.js"></script>
<script src="<%=basePath%>static/bootstrap-table/dist/extensions/export/bootstrap-table-export.js"></script>
<script src="<%=basePath%>static/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
<script src="<%=basePath%>static/bootstrap/js/bootstrap.js"></script>
<!-- 自定义js -->
<script>
    baselocation=${ctx};
    InitSubTable = function (index, row, $detail) {
        var parentid = row.categoryId;
        var cur_table = $detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            url: baselocation + '/product/category/gid/' + parentid,
            method: 'get',
            sidePagination: 'server',
            clickToSelect: true,
            uniqueId: "categoryId",
            pagination: true,
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
            },],
            onExpandRow: function (index, row, $Subdetail) {
                oInit.InitSubTable(index, row, $Subdetail);
            }
        });
    };

</script>
</body>
</html>