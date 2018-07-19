<%@ page contentType="text/html; charset=utf-8" language="java"  %>
<%@ include file="/WEB-INF/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>日志</title>
    <link rel="stylesheet" herf="${ctxsta}/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
    <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
</head>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<table id="table"></table>
<!-- Bootstrap table -->
<script src="${ctxsta}/bootstrap-table/dist/bootstrap-table.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/extensions/export/bootstrap-table-export.js"></script>
<script src="${ctxsta}/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>

<script src="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<!-- 自定义js -->
<script>
    baselocation="${ctx}";
    var $table = $('#table');
    $(function () {
        $table.bootstrapTable({
            url: baselocation + '/user/list/',
            height: '1000',
            striped: true,
            search: true,
            pageNumber:1,
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
            idField: 'userId',
            maintainSelected: true,
            sortName: 'userId',
            sortOrder: 'desc',
            columns: [
                {field: 'userId', title: '员工ID', sortable: true, align: 'center', halign: 'center'},
                {field: 'loginName', title: '登录账号', sortable: true, align: 'center', halign: 'center'},
                {field: 'userName', title: '员工名字', sortable: true, align: 'center', halign: 'center'},
                {field: 'sex', title: '性别', sortable: true, align: 'center', halign: 'center'},
                {field: 'age', title: '年龄', sortable: true, align: 'center', halign: 'center'},
                {field: 'picImg', title: '照片信息', sortable: true, align: 'center', halign: 'center'},
                {field: 'status', title: '身份类别', sortable: true, align: 'center', halign: 'center'},
                {field: 'telephone', title: '号码', sortable: true, align: 'center', halign: 'center'},
                {field: 'createTime', title: '创建时间', sortable: true, align: 'center', halign: 'center'},
                {field: 'updateTime', title: '更新时间', sortable: true, align: 'center', halign: 'center'},
                {field: 'operation', title: '操作', formatter: 'actionFormatter', events:'actionEvents'}
            ]
        });
    });

    function detailFormatter(index, row)
    {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }

    function timeFormatter(value)
    {
        return new Date(value).Format("yyyy-MM-dd HH:mm:ss");
    }

    //value: 所在column的当前显示值，
    //row:整个行的数据 ，对象化，可通过.获取
    //表格-操作 - 格式化
    function actionFormatter(value, row, index)
    {
        return '<a class="update" >修改</a> ' + '<a class="delete">删除</a>';
    }

    //表格  - 操作 - 事件`
    window.actionEvents =
        {            'click .delete' :  function(e, value, row, index)
        {
            var array= $('#table').bootstrapTable('getSelections');

            var msg = "您真的确定要删除吗？";
            if (confirm(msg) == true) {
                $.ajax({
                    url: "/user/list/"+row.userId,
                    type: "delete",
                    success: function (data)
                    {
                        //重新加载记录
                        //重新加载数据
                        $("#user").bootstrapTable('refresh', {url: '/user/list'});
                    }
                });
            }

        }
        }


    function delRow(k)
    {
        var a=document.getElementById("test").getElementsByTagName("ul")[0];
        var b=a.getElementsByTagName("li");
        var index;
        for(i=0;i<b.length;i++)
        {
            if(b[i].id=="li"+k)
            {
                index=i;
                break;
            }
        }
        var c=a.children(index);
        a.removeChild(c);
    }
</script>
</body>
</html>
