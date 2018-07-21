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
        body{
            width: 100%;
            height: 100%;
        }
        .main{
        width:100%;
        margin-top: 10%;
        }
        #theImage{
            width: 100%;
        }
        .theButton{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            flex-direction: row;
            justify-content: center;
            align-items: center;

        }
        .image{
            width: 80%;
            margin-bottom: 20%;
            margin-right: auto;
            margin-left: auto;
            margin-top: 10%;
        }

        .button{
            display: flex;
            display: -webkit-flex; /*兼容问题*/
            display: -ms-flexbox;
            display: inline-block;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            margin-bottom: 10%;
            color: white;

        }
        .name{

            margin-top: 20%;
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            margin-bottom: 10%;
        }
        .description{

            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            margin-bottom: 10%;
        }
        .price{
            /*width: 100%;*/
            /*height:22%;*/
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            margin-bottom: 10%;
        }
        .remain{
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            margin-bottom: 20%;
        }
        #nowPrice{
            color:#408eba;
            font-size: 30px;
        }
        .amount{

            display: flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 10%;

        }

    </style>
    <script src="https://cdn.staticfile.org/jquery/3.1.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"/>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
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
<body style="background-color:#CEE8FA">
<div class="container main">
    <div class="row" style="align-content: center;text-align: center;justify-content: center;height: 90%">
        <div class="col-md-5 col-md-offset-1" style="opacity: 0.7;background-color: white;height: 80%;border-radius: 5px;">
        <div class="row">
            <div class="image">
                <img id="theImage" src="${product.picImg}"/>
            </div>
            <div class="leftFoot">
                <div class="amount">
                    <label for="productNumber" class="left">购买数：</label>
                    <div><input type="text" id="productNumber" value="1"/></div>
                </div>
                <div class="theButton">
                    <div class="button"><button style="background-color: #408eba; border-radius: 5px;" type="button" onclick="addToOrder()"}>添加进购物车</button></div>
                    <div class="button"><button style="background-color: #408eba; border-radius: 5px;" type="button" onclick="back()">狠心离开</button></div>
                </div>
            </div>
        </div>
        </div>
        <div class="col-md-4 col-md-offset-1" style="opacity: 0.7;background-color: white;height: 80%;border-radius: 5px;">
            <div class="name">
                <div><h4>${product.productName}</h4></div>
            </div>
            <div class="description">
            <div><p>${product.productIntroduce}</p></div>
            </div>
            <div class="description">
                <div><p>${product.remarks}</p></div>
            </div>
            <div class="remain">
                <div class="left">剩余数量：</div><div id="nowRemain">${product.stock}</div>
            </div>
            <div class="price">
                <div class="left">价格：</div><div id="nowPrice">￥${product.productPrice}</div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
