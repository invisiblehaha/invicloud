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

<html>
<head>
    <title>所有商品</title>
    <script type="text/javascript">baselocation="${ctx}"</script>
    <link rel="stylesheet" href="${ctxsta}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>

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
            background-color:#408EBA;
            color: white;

        }

        .modal-body {padding: 2px 16px;}

        .modal-footer {
            padding: 2px 16px;
            background-color: #408EBA;
            color: white;
            align-items: center;

        }
    </style>

    <script>
        function add() {
            var info= {
                productId:$("#a").val(),
                productName:$("#b").val(),
                productPrice:$("#c").val(),
                stock:$("#d").val()

            };
            $.ajax({
                url:"${pageContext.request.contextPath}/product/product/instinct",
                type:"post",
                data: info,
                dataType:"json",
                success: function (data)
                {
                    window.location.href = baselocation + 'view';
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
        <small>库存管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>库存管理</a></li>
        <li class="active">商品管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content container-fluid">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>

<table id="table"></table>

<script src="<%=basePath%>static/bootstrap-table/dist/bootstrap-table.js"></script>
<script src="<%=basePath%>static/bootstrap-table/dist/extensions/export/bootstrap-table-export.js"></script>
<script src="<%=basePath%>static/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
<script src="<%=basePath%>static/bootstrap/js/bootstrap.js"></script>

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
            //showColumns: true,
            minimumCountColumns: 2,
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
            idField: 'productId',
            maintainSelected: true,
            sortName: 'productId',
            sortOrder: 'desc',
            columns: [
                {field: 'productId', title: '编号', sortable: true, align: 'center', halign: 'center'},
                {field: 'productName', title: '商品名称', align: 'center', halign: 'center'},
                {field: 'productPrice', title: '价格', align: 'center', halign: 'center'},
//                {field: 'productIntroduce', title: '商品介绍', align: 'center',  halign: 'center'},
                {field: 'stock', title: '库存', align: 'center',  halign: 'center'},
//                {field: 'remarks', title: '备注', align: 'center', halign: 'center'},
                {field: 'analy', title: '分析预测', formatter: 'operateFormatter', events:'operateEvents'},
		 {field: 'operation', title: '操作', formatter: 'actionFormatter', events:'actionEvents'}
            ]
        });
    });
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
                        url: "/product/product/"+ row.productId,
                        type: "delete",
                        success: function (data)
                        {
                            //重新加载记录
                            //重新加载数据
                            window.location.href = baselocation + 'view';
                        }
                    });
                }

            }
        };

    function detailFormatter(index, row) {
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

    function operateFormatter(value, row, index)
    {
        return '<button class="analy" type="button" >分析预测</button> ';
    }
    window.operateEvents =
        { 'click .analy' :  function(e, value, row, index){
        var pid=row.productId;
                $.ajax({
                    type: 'get',
                    url: "/product/productPredict/view?id="+pid,
                    success : function(){
                        window.location.href ="/product/productPredict/view?id="+pid
                    }
                });
            }

        };


</script>
</section>
<!-- 打开弹窗按钮 -->
<button id="myBtn">打开弹窗</button>

<!-- 弹窗 -->
<div id="myModal" class="modal">

    <!-- 弹窗内容 -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close">&times;</span>
            <h2>输入商品信息</h2>
        </div>
        <div class="modal-body">
            <form>
                <label>商品名称</label>
                <input type="text" id="b" name="productName" required="required" />
                <label>价格</label>
                <input type="text" id="c" name="productPrice" required="required" />
                <label>库存</label>
                <input type="text" id="d" name="stock" required="required" />
                <button type="button" onclick="add()">确认提交</button>

            </form>
        </div>
        <div class="modal-footer">
<br>
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
    };

    // 点击 <span> (x), 关闭弹窗
    span.onclick = function() {
        modal.style.display = "none";
    };

    // 在用户点击其他地方时，关闭弹窗
    window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
</script>
</body>
</html>
