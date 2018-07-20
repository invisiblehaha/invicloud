<%--
  Created by IntelliJ IDEA.
  User: 木水
  Date: 2018/7/18
  Time: 14:11
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
    <title>商品详情</title>
    <style type="text/css">
        .container{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            flex-direction: column;
            text-align: center;
            width: 100%;
            height: 100%;
            align-items: center;
            position: relative;
            overflow: hidden;
            justify-content: center;
        }
        .header{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            flex-direction: row;
            text-align: center;
            width: 100%;
            height: 15%;
            align-items: center;
            position: relative;
            justify-content: center;
            background: #69c6e1 ;
        }
        .header div{
            font-family: STHeiti;
            font-size: 30px;
            color: #ffffff;
        }
        .main{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            flex-direction: row;
            text-align: center;
            width: 100%;
            height: 85%;
            align-items: center;
            position: relative;
            justify-content: center;
        }
        .left{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            flex-direction: column;
            width: 35%;
            height:100%;
            text-align: center;
            align-items: center;

        }
        .right{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            flex-direction: column;
            width: 50%;
            height:100%;
            text-align: center;
            align-items: center;
        }
        .image{
            width: 100%;
            height:75%;

        }
        #theImage{
            width: 100%;
            height:100%;
        }
        .theButton{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            width: 100%;
            height:22%;
        }
        .button{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }
        .description{
            width: 100%;
            height:22%;
        }
        .price{
            width: 100%;
            height:22%;
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
        }
        #nowPrice{
            color:red;
            font-size: 30px;
        }
        .amount{
            width: 100%;
            height:22%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
    <script src="https://cdn.staticfile.org/jquery/3.1.1/jquery.min.js"></script>
    <script>
        $(function(){
            var storage=window.localStorage;
            var products=JSON.parse(storage.getItem("allProducts"));
            for(var i=0; i<products.length; i++) {
                if(products[i].productId == ${product.productId}) {
                    $("#productNumber").val(products[i].productNumber);
                    break;
                }
            }
        })
        function addToOrder() {
            if($("#productNumber").val()<=0) {
                alert("请重新输入购买件数！")
                return false;
            }
            if(!window.localStorage){
                alert("浏览器不支持localstorage");
                return false;
            }else{
                var storage=window.localStorage;
                var products=JSON.parse(storage.getItem("allProducts"));

                for(var i=0; i<products.length; i++) {
                    if(products[i].productId == ${product.productId}) {
                        products.splice(i,1);
                        console.log("成功删除");
                        break;
                    }
                }

                var product={productId:${product.productId},productNumber:$("#productNumber").val(),productPrice:${product.productPrice},productName:"${product.productName}"};
                products.push(product);
                storage.setItem("allProducts",JSON.stringify(products));
                console.log("验证localStorage存入商品信息:id:"+products[products.length-1].productId);
            }
            window.location.href="/store/shopping/shopping/view";
        }
        function back() {
            window.location.href="/store/shopping/shopping/view";
        }
    </script>
</head>
<body>
<div class="container">
    <div class="header">
        <div>${product.productName}</div>
    </div>
    <div class="main">
        <div class="left">
            <div class="image">
                <img id="theImage" src="${product.picImg}"/>
            </div>

        </div>
        <div class="right">
            <div class="description">
                <div><p>${product.productIntroduce}</p></div>
            </div>
            <div class="price">
                <div>价格：</div><div id="nowPrice">￥${product.productPrice}</div>
            </div>
            <div class="amount">
                <div><p>请输入购买件数:</p> </div>
                <div><input type="text" id="productNumber" value="1"/></div>
            </div>
            <div class="theButton">
                <div class="button"><button type="button" onclick="addToOrder()"}>添加进购物车</button></div>
                <div class="button"><button type="button" onclick="back()">狠心离开</button></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
