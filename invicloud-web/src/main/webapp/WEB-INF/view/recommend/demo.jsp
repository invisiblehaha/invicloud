<%@ page import="cn.vision.invicloud.support.entity.Customer" %>
<%@ page contentType="text/html; charset=utf-8" language="java"  %>
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
    <title>客户详情</title>
    <script type="text/javascript">baselocation="${ctx}"</script>
    <link rel="stylesheet" href="${ctxsta}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/css/css2.css"/>
    <style type="text/css">
        /* 弹窗 (background) */
        .modal {
            display: none; /* 默认隐藏 */
            position: fixed; /* 固定定位 */
            z-index: 1; /* 设置在顶层 */
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.4);
            -webkit-animation-name: fadeIn;
            -webkit-animation-duration: 0.4s;
            animation-name: fadeIn;
            animation-duration: 0.4s
        }

        /* 弹窗内容 */
        .modal-content {
            background-color: #2d6ca2;
            margin: 10% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 50%;
            -webkit-animation-name: slideIn;
            -webkit-animation-duration: 0.4s;
            animation-name: slideIn;
            animation-duration: 0.4s
        }

        /* 关闭按钮 */
        .close {
            color: #00c0ef;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
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
            background-color: #8959a8;
            color: white;
        }
        @-webkit-keyframes slideIn {
            from {bottom: -300px; opacity: 0}
            to {bottom: 0; opacity: 1}
        }

        @keyframes slideIn {
            from {bottom: -300px; opacity: 0}
            to {bottom: 0; opacity: 1}
        }

        @-webkit-keyframes fadeIn {
            from {opacity: 0}
            to {opacity: 1}
        }

        @keyframes fadeIn {
            from {opacity: 0}
            to {opacity: 1}
        }
    </style>
    <script>
        function searchForItem() {
            var info={  userInput:$("#inputVal").val()  };
            $.ajax({
                url:"${pageContext.request.contextPath}/recommend/demo/search",
                method:"post",
                dataType:"json",
                data:info,
                success:function (searchResult) {
                    $(".quarter2").children("div.columns").remove();
                    for(var i=0;i<searchResult.length;i++)
                    {
                        for(var i=0;i<searchResult.length;i++) {
                            $("<div class='columns'>");
                            $(".quarter2").append("<div class='columns'>");
                            $(".quarter2").children("div:last-child").append("<a href='productDetail.jsp?id="+searchResult[i].productId+"'></a>");
                            $(".quarter2").children("div:last-child").append("<small>"+searchResult[i].productName+"</small>");
                            $(".quarter2").children("div:last-child").children("a").append("<p class='thumbnail_align'></p>");
                            $(".quarter2").children("div:last-child").children("a").children("p").append("<img src='"+searchResult[i].picImg+"' class='thumbnail'/>");
                        }
                    }
                },
                error:function () {
                }
            })
        }

        var firstSelectedId=0;
        var secondSelectedId=0;
        var categ={};
        function loadCate() {
            $.ajax({
                url:"${pageContext.request.contextPath}/recommend/demo/cate",
                method:"post",
                data:{},
                dataType:"json",
                success:function (cate) {
                    categ=cate;
                    for(var i=0;i<cate["0"].length;i++) {
                        console.log(cate[0][i].categoryId);
                        var id=cate[0][i].categoryId;
                        $(".first-cate").append("<a href='#'id='"+id+"' onclick='loadSecondCate("+id+")'>"+cate[0][i].categoryName+"</a>");
                    }
                },
                error:function (cate) {
                    alert("fail");
                }
            })
        }

        function loadSecondCate(id)
        {
            secondSelectedId=0;
            firstSelectedId=id;
            $(".second-cate").empty();
            for(var i=0;i<categ[id].length;i++) {
                $(".second-cate").append("<a href='#' id='"+categ[id][i].categoryId+"' onclick=setSecondId("+categ[id][i].categoryId+")>"+categ[id][i].categoryName+"</a>");
            }
        }

        function setSecondId(id){
            secondSelectedId=id;
        }

        function confirmCate() {
            console.log("first"+firstSelectedId);
            console.log("second"+secondSelectedId);
            if(firstSelectedId==0&&secondSelectedId==0)
                return;
            else if(firstSelectedId!=0&&secondSelectedId==0)
            {
                $.ajax({
                    url:"${pageContext.request.contextPath}/recommend/demo/cateProduct",
                    method:"post",
                    data:{id:firstSelectedId},
                    dataType:"json",
                    success:function(searchResult){
                        $(".quarter2").children("div.columns").remove();
                        for(var i=0;i<searchResult.length;i++)
                        {
                            for(var i=0;i<searchResult.length;i++) {
                                $("<div class='columns'>");
                                $(".quarter2").append("<div class='columns'>");
                                $(".quarter2").children("div:last-child").append("<a href='productDetail.jsp?id="+searchResult[i].productId+"'></a>");
                                $(".quarter2").children("div:last-child").append("<small>"+searchResult[i].productName+"</small>");
                                $(".quarter2").children("div:last-child").children("a").append("<p class='thumbnail_align'></p>");
                                $(".quarter2").children("div:last-child").children("a").children("p").append("<img src='"+searchResult[i].picImg+"' class='thumbnail'/>");
                            }
                        }
                        
                    },
                    error:function () {
                    }
                })
            }
            else{
                $.ajax({
                    url:"${pageContext.request.contextPath}/recommend/demo/cateProduct",
                    method:"post",
                    data:{id:secondSelectedId},
                    dataType:"json",
                    success:function(searchResult){
                        $(".quarter2").children("div.columns").remove();
                        for(var i=0;i<searchResult.length;i++)
                        {
                            for(var i=0;i<searchResult.length;i++) {
                                $("<div class='columns'>");
                                $(".quarter2").append("<div class='columns'>");
                                $(".quarter2").children("div:last-child").append("<a href='productDetail.jsp?id="+searchResult[i].productId+"'></a>");
                                $(".quarter2").children("div:last-child").append("<small>"+searchResult[i].productName+"</small>");
                                $(".quarter2").children("div:last-child").children("a").append("<p class='thumbnail_align'></p>");
                                $(".quarter2").children("div:last-child").children("a").children("p").append("<img src='"+searchResult[i].picImg+"' class='thumbnail'/>");
                            }
                        }
                    },
                    error:function () {

                    }
                })
            }
        }

    </script>
</head>

<body style="width: 100%">
<section class="content-header">
    <h1>
        InvisiCloud
        <small>推荐系统</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>推荐系统</a></li>
        <li class="active">${customer.getCustomerId()}</li>
    </ol>
</section>
<!-- Main content -->
<section class="content container-fluid">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="${ctxsta}/bootstrap/js/bootstrap.js"></script>
<div class="container-left">
<div class="quarter1">
<table class="person">
    <tr>
        <td class="lefttd">客户名：</td>
        <td>${customer.realName}</td>
    </tr>
    <tr>
        <td class="lefttd">性别：</td>

        <td>
            <c:if test="${customer.sex eq 0}">保密</c:if>
            <c:if test="${customer.sex eq 1}">男</c:if>
            <c:if test="${customer.sex eq 2}">女</c:if>
        </td>
    </tr>
    <tr>
        <td class="lefttd">喜好列表：</td>
        <td>${likeList}</td>
    </tr>
    <tr>
        <td class="lefttd">平均消费金额：</td>
        <td>${averageMoney}</td>
    </tr>
    <tr>
        <td class="lefttd">平均消费数量：</td>
        <td>${averageNum}</td>
    </tr>
    <tr>
        <td class="lefttd">分类饼图</td>
        <td>${catmap}</td>
    </tr>
</table>
</div>
    <div class="quarter3">
        <h5>推荐商品：</h5>
<c:forEach items="${goods}" var="good">
    <div class="columns">
        <a href="<%=basePath%>recommend/product/view?id=${good.productId}&customerId=${customer.customerId}"><p class="thumbnail_align"> <img src="${good.picImg}" class="thumbnail"/> </p></a>
        <small>${good.productName}</small>
    </div>
</c:forEach>
    </div>
</div>
    <div class="container-right">
    <div class="quarter2">
        <nav>商品列表</nav>


        <!-- 打开弹窗按钮 -->
        <button type="button" id="myBtn" onclick="loadCate()">打开弹窗</button>




        <input type="text" id="inputVal" name="searchItem">
        <button type="button" onclick="searchForItem()">搜索</button>
        <c:forEach items="${products}" var="product">
            <div class="columns">
                <a href="<%=basePath%>recommend/product/view?id=${product.productId}&customerId=${customer.customerId}"><p class="thumbnail_align"> <img src="${product.picImg}" class="thumbnail"/> </p></a>
                <small>${product.productName}</small>
            </div>
        </c:forEach>
    </div>
    <div class="quarter4">
        <button id="createOrder">生成订单</button>
<div class="orderItem">
    <div class="one">
        <img src="#"/>
        <p>商品描述</p>
    </div>
    <div class="two">

    </div>
    <div class="three">

    </div>

</div>
        <div class="orderItem">
            <div class="one">
                <img src="#"/>
                <p>商品描述</p>
            </div>
            <div class="two">

            </div>
            <div class="three">
            </div>
        </div>
    </div>
        <!-- 弹窗 -->
        <div id="myModal" class="modal">
            <!-- 弹窗内容 -->
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                </div>
                <div class="modal-body">
                    <div class="first-cate"></div>
                    <div class="second-cate"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="confirmCate()"></button>
                </div>
        </div>
    </div>
    </div>
</section>


<script>
    // 获取弹窗
    var modal = document.getElementById('myModal');

    // 打开弹窗的按钮对象
    var btn = document.getElementById("myBtn");

    // 获取 <span> 元素，用于关闭弹窗
    var span = document.querySelector('.close');

    // 点击按钮打开弹窗
    btn.onclick = function() {
        modal.style.display = "block";
        loadCate();
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
</body>
</html>