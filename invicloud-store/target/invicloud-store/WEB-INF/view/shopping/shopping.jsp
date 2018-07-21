<%--
  Created by IntelliJ IDEA.
  User: 木水
  Date: 2018/7/18
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>商店模拟模块</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script type="text/javascript">baselocation="${ctx}"</script>
    <script src="https://cdn.staticfile.org/jquery/3.1.1/jquery.min.js"></script>
<%--    <link rel="stylesheet" href="${ctxsta}/css/css2.css"/>--%>
    <style type="text/css">
        body{
           width: 100%;
            height: 100%;
            margin: auto;
        }
        .container-right{
            display: flex;
            flex-direction: column;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            text-align: center;
            width: 100%;
            height: 100%;
            align-items: center;
            position: relative;
            overflow: hidden;
            justify-content: left;
        }
        .header{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            width: 100%;
            height:10%;
            text-align: center;
            align-items: center;
            overflow-y:auto;
            justify-content: center;
        }
        .main{
            display: flex;
            flex-direction: row;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            width: 100%;
            height:80%;
            text-align: center;
            align-items: center;
            position: relative;
            overflow-y:auto;
            justify-content: center;
            overflow: hidden;
        }
        .left{
            border-radius: 5px;
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            width: 15%;
            height:99%;
            text-align: left;
            align-items: center;
            position: relative;
            overflow-y:auto;
            justify-content: center;
            border: solid 1px #408EBA;


        }
        .quarter2{
            border-radius: 5px;
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            width: 46%;
            height:99%;
            text-align: center;
            align-items: center;
            position: relative;
            overflow-y:auto;
            justify-content: center;
            border: solid 1px #408EBA;

        }
        .quarter4{
            border-radius: 5px;
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            width: 36%;
            height:99%;
            text-align: center;
            align-items: center;
            position: relative;
            overflow-y:auto;
            justify-content: center;
            border: solid 1px #408EBA;

        }
        nav{
            height: 5%;
            background-color: #408EBA;
            opacity: 0.7;
            color: #ffffff;
        }
        .columns {
            width: 20%;
            height: 20%;
            float: left;
            /*line-height: 24px;*/
            /*padding-top: 10px;*/

            text-align: justify;
            margin: 0 auto 15%;
        }
        .thumbnail_align {
            text-align: center;
        }
        .thumbnail {
            width: 80%;
            height: 80%;
            margin-left: auto;
        }
        .bar1 {
            position: relative;
            width: 40%;
            margin: 0 auto;
            border: none;
            outline: none;
        }
        .bar1 input {
            width: 100%;
            height: 42px;
            padding-left: 13px;
            border: 2px solid  #408EBA;
            border-radius: 5px;
            color: #000000;
        }
        .bar1 button {
            height: 42px;
            width: 42px;
            cursor: pointer;
            position: absolute;
            top: 0;
            right: 0;
            background:  #408EBA;
            border-radius: 0 5px 5px 0;
        }
        .bar1 button:before {
            content: "\f002";
            font-family: FontAwesome;
            font-size: 16px;
            color: #F9F0DA;
        }
        table {
            border-collapse: collapse;
            text-align: center;
            width: 100%;
        }
        table td, table th
        {
            border:solid 1px lightblue;
            color: #000000;
            height: auto;
           padding-right: 3px;
            width: 20%;

        }
        table thead th
        {
            background-color: #62afde;
            opacity: 0.7;
        }
        table tr:nth-child(odd)
        {
            background: #fff;
        }
        table tr:nth-child(even)
        {
            background: #F5FAFA;
        }
        .left ul{
            list-style: none;
            margin:3px;
        }
        .left ul li{
            margin: 3px;
        }
        a {text-decoration:none;}
        a:link {color:#000000;text-decoration:none;}
        a:visited {color:#000000}
        a:hover {text-decoration:underline;color:#408EBA}
        a:active {color:#000000}
        #createOrder{
            width: 10%;
            height: 4%;
            border-radius:5px;
            background: #408eba;
            color: white;
            align-self: flex-end;
            margin-right: 2%;
            margin-bottom: 2%;

        }
    </style>
    <script>
        window.alert = function(str)
        {
            var shield = document.createElement("DIV");
            shield.id = "shield";
            shield.style.position = "absolute";
            shield.style.left = "0px";
            shield.style.top = "0px";
            shield.style.width = "100%";
            shield.style.height = document.body.scrollHeight+"px";
            //弹出对话框时的背景颜色
            //shield.style.background = "#fff";
            shield.style.textAlign = "center";
            shield.style.zIndex = "25";
            shield.style.opacity="0.95";
            var alertFram = document.createElement("DIV");
            alertFram.id="alertFram";
            alertFram.style.position = "absolute";
            alertFram.style.left = "50%";
            alertFram.style.top = "50%";
            alertFram.style.marginLeft = "-225px";
            alertFram.style.marginTop = "-75px";
            alertFram.style.width = "450px";
            alertFram.style.height = "120px";
            alertFram.style.background = "#ffffff";
            alertFram.style.textAlign = "center";
            alertFram.style.lineHeight = "150px";
            alertFram.style.zIndex = "300";
            alertFram.style.opacity="0.95";
            strHtml = "<ul style=\"list-style:none;margin:0px;padding:0px;width:100%\">\n";
            strHtml += " <li style=\"background:#fff;text-align:center;font-family:Microsoft YaHei;font-size:20px;height:120px;line-height:120px;border:1px solid #408EBA;border-radius: 5px;\">"+str+"</li>\n";
            strHtml += "</ul>\n";
            alertFram.innerHTML = strHtml;
            document.body.appendChild(alertFram);
            document.body.appendChild(shield);
            //var ad = setInterval("doAlpha()",5);
            alertFram.focus();
            document.body.onselectstart = function(){return false;};
        };
        window.onclick= function(event){
            var shield=document.getElementById("shield");
            if (event.target ==shield) {
                $("#alertFram").remove();
                $("#shield").remove();
            }
        };
        $(function (){
            var storage=window.localStorage;
            var products=JSON.parse(storage.getItem("allProducts"));
            if(products.length>0) {
                console.log("在shopping界面得到信息:id:" + products[products.length - 1].productId);
                for(var i=0;i<products.length;i++) {
                    $(".quarter4").children("table").append("<tr><td id="+i+"productId>"+products[i].productId+"</td><td id="+i+"productName>"+products[i].productName+"</td><td id="+i+"productNumber>"+products[i].productNumber+"</td><td id="+i+"productPrice>"+products[i].productPrice*products[i].productNumber+"</td><td><button type='button' class='deleteTable' onclick='deleteRow(this)'>删除</button></td></tr>")
                }
            }
        })

        function deleteRow(obj){
            var tr = obj.parentNode.parentNode;
            var removeRow=obj.parentNode.parentNode.rowIndex;
            console.log("removerRow:"+removeRow);
            var removeId = $(".table tr:eq(" + removeRow + ") td:eq(0)").html()
            console.log("removeId:"+removeId);
            tr.parentNode.removeChild(tr);
            var storage=window.localStorage;
            var products=JSON.parse(storage.getItem("allProducts"));
            console.log("删之前长度："+products.length);
            for(var i=0;i<products.length;i++) {
                if(products[i].productId==removeId)
                {
                    products.splice(i,1);
                }
                storage.setItem("allProducts",JSON.stringify(products));
            }
            console.log("删之后长度："+products.length);
        }

        function searchForItem() {
            var info={  userInput:$("#inputVal").val()  };
            $.ajax({
                url:"${pageContext.request.contextPath}/shopping/shopping/search",
                method:"post",
                dataType:"json",
                data:info,
                success:function (searchResult) {
                    $(".quarter2").children("div.columns").remove();
                    for(var i=0;i<searchResult.length;i++)
                    {
                        for(var i=0;i<searchResult.length;i++) {
                            $("<div class='columns'>");
                            $(".quarter2").append("<div class='columns'></div>");
                            $(".quarter2").children("div:last-child").append("<a href='productDetail?productId="+searchResult[i].productId+"'></a>");
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

        var categ={};
        $(function(){
            console.log("here!!!");
            $.ajax({
                url:"${pageContext.request.contextPath}/shopping/shopping/cate",
                method:"post",
                data:{},
                dataType:"json",
                success:function (cate) {
                    categ=cate;
                    for(var i=0;i<cate["0"].length;i++) {
                        console.log(cate[0][i].categoryId);
                        var id=cate[0][i].categoryId;
                        $("#first-cate").append("<li id='"+id+"'  onmouseenter='loadSecondCate("+id+")' onmouseleave='loadSecondCate("+id+")'><a href='#' onclick='confirmCate("+categ[0][i].categoryId+")'>"+cate[0][i].categoryName+"</a></li>")
                    }
                },
                error:function (cate) {
                    alert("category fail");
                }
            })
        })

        function loadSecondCate(id)
        {
            if($("#"+id).children().length>1){
                $("#"+id).children("ul").remove();
            }
            else {
                $("#" + id).append("<ul></ul>");
                for (var i = 0; i < categ[id].length; i++) {
                    $("#" + id).children("ul:last-child").append("<li id='" + categ[id][i].categoryId + "'><a href='#' onclick='confirmCate("+categ[id][i].categoryId+")' >" + categ[id][i].categoryName + "</a></li>");
                }
            }
        }

        function confirmCate(cateId) {
            var flag=0;
            for(var i=0;i<categ["0"].length;i++) {
                if(categ[0][i].categoryId==cateId) {
                    flag=1;
                }
            }
            console.log("flag="+flag);
            if(flag)
            {
                $.ajax({
                    url:"${pageContext.request.contextPath}/shopping/shopping/cateProduct",
                    method:"post",
                    data:{id:cateId,flag:1},
                    dataType:"json",
                    success:function(searchResult){
                        $(".quarter2").children("div.columns").remove();
                        for(var i=0;i<searchResult.length;i++)
                        {
                            for(var i=0;i<searchResult.length;i++) {
                                $("<div class='columns'>");
                                $(".quarter2").append("<div class='columns'></div>");
                                $(".quarter2").children("div:last-child").append("<a href='productDetail?productId="+searchResult[i].productId+"'></a>");
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
                    url:"${pageContext.request.contextPath}/shopping/shopping/cateProduct",
                    method:"post",
                    data:{id:cateId,flag:0},
                    dataType:"json",
                    success:function(searchResult){
                        $(".quarter2").children("div.columns").remove();
                        for(var i=0;i<searchResult.length;i++)
                        {
                            for(var i=0;i<searchResult.length;i++) {
                                $("<div class='columns'>");
                                $(".quarter2").append("<div class='columns'></div>");
                                $(".quarter2").children("div:last-child").append("<a href='productDetail?productId="+searchResult[i].productId+"'></a>");
                                $(".quarter2").children("div:last-child").append("<small>"+searchResult[i].productName+"</small>");
                                $(".quarter2").children("div:last-child").children("a").append("<p class='thumbnail_align'></p>");
                                $(".quarter2").children("div:last-child").children("a").children("p").append("<img src='"+searchResult[i].picImg+"' class='thumbnail'/>");
                            }
                        }
                    }
                })
            }
        }

        function createOrder(){
            var orderLength=$(".quarter4").children("table").children("tr").length;
            if(orderLength==0){
                alert("请添加商品！");
                return false;
            }
            console.log(orderLength);
            console.log($(".quarter4").children().length);
            console.log($(".quarter4").children("table").children().length);
            var productAccount=0;
            var productTotalPrice=0;
            for(var i=0;i<orderLength;i++){
                var productId=document.getElementById(i+"productId").innerText;
                var productNumber=document.getElementById(i+"productNumber").innerText;
                var productPrice=productNumber*document.getElementById(i+"productPrice").innerText;
                /*console.log(i+"productId");
                console.log(document.getElementById(i+"productId").innerText);
                console.log(document.getElementById(i+"productNumber").innerText);
                console.log(document.getElementById(i+"productPrice").innerText);
                console.log(productPrice);*/
                productAccount+=parseInt(productNumber);
                productTotalPrice+=productPrice;
            }

            var storage=window.localStorage;
            var customerId=storage.getItem("loginId");
            console.log(productAccount);
            $.ajax({
                url:"${pageContext.request.contextPath}/shopping/shopping/addOrderToDB",
                type:"post",
                data:{customerId:customerId,productAccount:productAccount,productTotalPrice:productTotalPrice},
                async:false,
                success:function (orderId) {
                    console.log("成功创建"+orderId+"号订单");
                    for(var i=0;i<orderLength;i++) {
                        var productId=document.getElementById(i+"productId").innerText;
                        var productNumber=document.getElementById(i+"productNumber").innerText;
                        var productPrice=productNumber*document.getElementById(i+"productPrice").innerText;
                        $.ajax({
                            url:"${pageContext.request.contextPath}/shopping/shopping/addOrderItemToDB",
                            type:"post",
                            data:{orderId:orderId,productId:productId,productNumber:productNumber,productPrice:productPrice},
                            success:function () {
                                console.log("成功将"+productId+"号商品写入"+orderId+"号订单");
                            }
                        })
                    }
                }
            })
            $('.table tr:gt(0)').remove();
            var storage=window.localStorage;
            var products=JSON.parse(storage.getItem("allProducts"));
            products.splice(0,products.length);
            storage.setItem("allProducts",JSON.stringify(products));
            console.log("删之后长度："+products.length);
            alert("成功生成订单！");
        }
    </script>
</head>
<body style="width: 100%">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<div class="container-right">
    <div class="header">
        <form class="bar1">
            <input type="text" placeholder="请输入您要搜索的内容..." id="inputVal" name="searchItem" autocomplete="off"/>
            <button type="button" onclick="searchForItem()"></button>
        </form>
    </div>
    <div class="main">
        <div class="left">
            <nav style="padding-left: 30%">商品分类</nav>
            <ul id="first-cate">
            </ul>
        </div>
        <div class="quarter2">
        <nav>商品列表</nav>
        <c:forEach items="${products}" var="product">
            <div class="columns">
                <a href="productDetail?productId=${product.productId}"><p class="thumbnail_align"> <img src="${product.picImg}" class="thumbnail"/> </p></a>
                <small>${product.productName}</small>
            </div>
        </c:forEach>
    </div>
        <div class="quarter4">
            <nav>购物车</nav>

            <table width="90%" class="table">
                <thead>
                <tr>
                    <th>
                        编号
                    </th>
                    <th>
                        名称
                    </th>
                    <th>
                        数量
                    </th>
                    <th>
                        价格
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
                </thead>
            </table>

        </div>
    </div>

    <button id="createOrder" onclick="createOrder()">生成订单</button>

</div>
</body>
</html>
