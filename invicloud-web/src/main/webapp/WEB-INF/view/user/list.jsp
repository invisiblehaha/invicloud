<%@ page contentType="text/html; charset=utf-8" language="java"  %>
<%@ include file="/WEB-INF/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户</title>
    <link rel="stylesheet" herf="${ctxsta}/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
    <link href="//cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet"/>
   <style type="text/css">
        /* 弹窗 (background) */
        .modal {
            display: none; /* 默认隐藏 */
            position: fixed;
            z-index: 1;
            padding-top: 100px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.4);
        }

        /* 弹窗内容 */
        .modal-content {
            position: relative;
            background-color: #fefefe;
            margin: auto;
            padding: 0;
            border: 1px solid #888;
            width: 50%;
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
            -webkit-animation-name: animatetop;
            -webkit-animation-duration: 0.4s;
            animation-name: animatetop;
            animation-duration: 0.4s
        }

        /* 添加动画 */
        @-webkit-keyframes animatetop {
            from {top:-300px; opacity:0}
            to {top:0; opacity:1}
        }

        @keyframes animatetop {
            from {top:-300px; opacity:0}
            to {top:0; opacity:1}
        }

        /* 关闭按钮 */
        .close {
            color: white;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        .modal-header {
            padding: 2px 16px;
            background-color: #5cb85c;
            color: white;
        }

        .modal-body {padding: 2px 16px;}
        .modal-footer {
            padding: 2px 16px;
            background-color: #5cb85c;
            color: white;
        }
    </style>

    <script>
        function add() {
           var info= {
               userId:$("#a").val(),
               loginName:$("#b").val(),
               loginPassword:$("#c").val(),
               userName:$("#d").val(),
               sex:$("#e").val(),
               age:$("#f").val(),
               status:$("#g").val(),
               telephone:$("#h").val(),
               picImg:$("#i").val()
           }
            $.ajax({
               url:"${pageContext.request.contextPath}/user/list/instinct",
               type:"post",
                data: info,
                dataType:"json",
                success: function (data)
                {

                },
                error:function(){

                }

            })

        }
      </script>
</head>
<body>
<section class="content-header">
    <h1>
        InvisiCloud
        <small>用户管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>用户管理</a></li>
    </ol>
</section>


<!-- Main content -->
<section class="content container-fluid">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
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
            editable: true,
            columns: [
                {field: 'userId', title: '员工ID', sortable: true, align: 'center', halign: 'center'},
                {field: 'loginName', title: '登录账号', sortable: true, align: 'center', halign: 'center'},
                {field: 'userName', title: '员工名字', sortable: true, align: 'center', halign: 'center'},
                {field: 'sex', title: '性别', sortable: true, align: 'center', halign: 'center'},
                {field: 'age', title: '年龄', sortable: true, align: 'center', halign: 'center'},
                {field: 'picImg', title: '照片信息', sortable: true, align: 'center', halign: 'center'},
                {field: 'status', title: '身份类别', sortable: true, align: 'center', halign: 'center'},
                {field: 'telephone', title: '号码', sortable: true, align: 'center', halign: 'center'},
                {field: 'createTime', title: '创建时间', sortable: true, align: 'center', halign: 'center', formatter: 'timeFormatter'},
                {field: 'updateTime', title: '更新时间', sortable: true, align: 'center', halign: 'center', formatter: 'timeFormatter'},
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

    //value: 所在column的当前显示值，
    //row:整个行的数据 ，对象化，可通过.获取
    //表格-操作 - 格式化
    function actionFormatter(value, row, index)
    {
        return '<a class="delete">删除</a>';
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
                    type: "get",
                    success: function (data)
                    {
                        window.location.href = "${pageContext.request.contextPath}/user/list/view"
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
<!-- 打开弹窗按钮 -->
<button id="myBtn">打开弹窗</button>

<!-- 弹窗 -->
<div id="myModal" class="modal">

    <!-- 弹窗内容 -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">&times;</span>
            <h2>弹窗头部</h2>
        </div>
        <div class="modal-body">
            <form>
                <p>员工ID</p>
                <input type="text" id="a" name="userId" required="required" />
                <p>登录名</p>
                <input type="text" id="b" name="loginName" required="required" />
                <p>登录密码</p>
                <input type="text" id="c" name="loginPassword" required="required" />
                <p>员工名字</p>
                <input type="text" id="d" name="userName" required="required" />
                <p>性别</p>
                <input type="text" id="e" name="sex" required="required" />
                <p>年龄</p>
                <input type="text" id="f" name="age" required="required" />
                <p>照片信息</p>
                <input type="text" id="i" name="picImg" required="required" />
                <p>身份</p>
                <input type="text" id="g" name="status" required="required" />
                <p>电话</p>
                <input type="text" id="h" name="telephone" required="required" />
                <button type="button" onclick="add()">Click Me!</button>

            </form>
        </div>
        <div class="modal-footer">
            <h3>弹窗底部</h3>
        </div>
    </div>

</div>
<script>
    // 获取弹窗
    var modal = document.getElementById('myModal');

    // 打开弹窗的按钮对象
    var btn = document.getElementById("myBtn");

    // 获取 <span> 元素，用于关闭弹窗 that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // 点击按钮打开弹窗
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // 点击 <span> (x), 关闭弹窗
    span.onclick = function() {
        modal.style.display = "none";
    }

    // 在用户点击其他地方时，关闭弹窗
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
</section>
</body>
</html>
