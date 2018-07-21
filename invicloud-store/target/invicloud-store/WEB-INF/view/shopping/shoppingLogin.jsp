
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
    //{pageContext.request.ContextPath}
    String path = request.getContextPath();
    // 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量
    String basePath = request.getServerName() + ":" + request.getServerPort()
            + path ;
    // 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。
    // pageContext.setAttribute("basePath", basePath);
%>
<%@ include file="/WEB-INF/base.jsp" %>

<html>
<head>
<title>会员登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"/>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <style>
        video{
            border:1px solid gray;
            width:400px;
            height:400px;
        }
        canvas{
            border:1px solid gray;
            width:400px;
            height:400px;
            position: absolute;
        }
    </style>
    <style>
        body{
            margin-left:auto;
            margin-right:auto;
            background: url("${ctxsta}/images/background.png")no-repeat;

        }
        #upper{
            align-items: center;
            align-content: center;
            text-align: center;
            width: 100%;
        }
        #upload{
            background-color: #003bb3;
            opacity: 0.5;
            color: white;
            border-radius: 5px;
            width: 10%;

        }
        .header{
            align-items: center;
            align-content: center;
            text-align: center;
            width: 100%;
            margin-top:5%;
            margin-bottom:5%;
        }
        .header h3{
            color: white;
            font-size: 5em;
            font-family: 'DejaVu Sans Mono', monospace;
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
            strHtml += " <li style=\"background:#fff;text-align:center;font-family:Microsoft YaHei;font-size:20px;height:120px;line-height:120px;border:1px solid #408EBA;\">"+str+"</li>\n";
            strHtml += "</ul>\n";
            alertFram.innerHTML = strHtml;
            document.body.appendChild(alertFram);
            document.body.appendChild(shield);
            //var ad = setInterval("doAlpha()",5);
            alertFram.focus();
            document.body.onselectstart = function(){return false;};
        };
        var img1;
        function btn_upload() {
            $.ajax({
                url : '${pageContext.request.contextPath}/shopping/shopping/checkLogin',
                type : 'post',
                dataType:"json",
                data:info,
                success : function(cusInfo) {
                    //获取到后端传来的responce 内容为{{"rows":[{key:value,},],"total":value}}的形式
                    if(cusInfo["code"]!=undefined)
                    {
                        if(cusInfo["code"]==20000)
                        {
                            alert(cusInfo["message"]);
                        }
                        else if(cusInfo["code"]==20004)
                        {
                            alert(cusInfo["message"]);
                        }
                        else alert("Sorry,fail...")
                    }
                    else{
                        if(cusInfo["total"]==0)
                        {
                            alert("Please upload clear picture!")
                        }
                        if(cusInfo["total"]==1)
                        {
                            alert("欢迎光临！"+cusInfo.rows[0].customerId+"号会员!")
                            //doSendUsers("1 "+cusInfo.rows[0].customerId);
                            if (!window.localStorage) {
                                alert("浏览器不支持localstorage");
                                return false;
                            } else {
                                var storage = window.localStorage;
                                var products = [];
                                storage.setItem("loginId", cusInfo.rows[0].customerId);
                                storage.setItem("allProducts", JSON.stringify(products));
                                console.log("登录界面存入用户id：" + storage.getItem("loginId"));
                                console.log("登录界面存入用户order订单数组");
                                window.location.href = '${pageContext.request.contextPath}/shopping/shopping/view';
                            }
                        }
                    }
                },
                error : function(error) {
                    alert("failed");
                }
            });
        }

    </script>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.js"></script>

</head>

<body>
<div class="header">
    <h3>InvisiCloud</h3>
</div>
<div id="upper">
    <video  id="myVideo" autoplay="autoplay" height="400" width="400" style="border: 1px solid #408eba; position: absolute;left: 230px;top:300px; object-fit: fill;border-radius: 5px"></video>
    <canvas id="myCanvasForVideo" height="400" width="400" style="border: 1px solid #408eba; position: absolute;left: 230px;top:300px;border-radius: 5px"></canvas>
    <canvas id="myCanvas" height="400" width="400" style="border: 1px solid #408eba; position: absolute;left: 650px;top:300px;border-radius: 5px"></canvas>

</div>
<div class="header">
    <div id="controls">
        <button type="button" id="upload">登录</button>
    </div>
</div>



<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/utils.js"></script>
<script src="${pageContext.request.contextPath}/static/js/clmtrackr.js"></script>
<script src="${pageContext.request.contextPath}/static/js/model_pca_20_svm.js"></script>
<script src="${pageContext.request.contextPath}/static/js/Stats.js"></script>
<script src="${pageContext.request.contextPath}/static/js/d3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/emotion_classifier.js"></script>
<script src="${pageContext.request.contextPath}/static/js/emotionmodel.js"></script>

<script type="text/javascript">
    function hasUserMedia(){//判断是否支持调用设备api，因为浏览器不同所以判断方式不同
        return !!(navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
    }



    pModel.shapeModel.nonRegularizedVectors.push(9);
    pModel.shapeModel.nonRegularizedVectors.push(11);//将特征向量9和11非正则化，以更好地探测眉毛的运动
    delete emotionModel['disgusted'];
    delete emotionModel['fear'];
    var ec = new emotionClassifier();
    ec.init(emotionModel);
    var emotionData = ec.getBlank();
    //tracking
    var info = {};
    var ctracker = new clm.tracker();
    var video = document.getElementById('myVideo');
    var canvas = document.getElementById('myCanvas');
    var context = canvas.getContext('2d');
    ctracker.init(pModel);
    var positionInitial = ctracker.getCurrentPosition();//获取最初的position，用作后续判断是否有人脸
    ctracker.start(video);
    function positionLoop() {
        requestAnimationFrame(positionLoop);
        var positions = ctracker.getCurrentPosition();
        img1 = canvas.toDataURL("image/png");//储存了img流，直接用这个的base64就行
        context.fillStyle="#ffffff";
        context.beginPath();
        context.fillRect(0,0,canvas.width,canvas.height);
        context.closePath();

        if(positions!= positionInitial)//如果video里有人脸
        {
            //画出摄像头捕捉的图像
            context.drawImage(video,0,0,400,400);
            //将获取的图片base64信息封装在info中
            info['imgString'] = img1;
        }
    }
    positionLoop();

    var canvasInput = document.getElementById('myCanvasForVideo');
    var contextInput = canvasInput.getContext('2d');
    function drawLoop()
    {
        requestAnimationFrame(drawLoop);
        contextInput.clearRect(0,0,canvasInput.width,canvasInput.height);
        ctracker.draw(canvasInput);
    }
    drawLoop();



    function updateData(data) {
        // update
        var rects = svg.selectAll("rect")
            .data(data)
            .attr("y", function(datum) { return height - y(datum.value); })
            .attr("height", function(datum) { return y(datum.value); });
        var texts = svg.selectAll("text.labels")
            .data(data)
            .attr("y", function(datum) { return height - y(datum.value); })
            .text(function(datum) { return datum.value.toFixed(1);});
        // enter
        rects.enter().append("svg:rect");
        texts.enter().append("svg:text");
        // exit
        rects.exit().remove();
        texts.exit().remove();
    }

    var margin = {top : 20, right : 20, bottom : 10, left : 40},
        width = 400 - margin.left - margin.right,
        height = 100 - margin.top - margin.bottom;
    var barWidth = 30;
    var formatPercent = d3.format(".0%");
    var x = d3.scale.linear()
        .domain([0, ec.getEmotions().length]).range([margin.left, width+margin.left]);
    var y = d3.scale.linear()
        .domain([0,1]).range([0, height]);
    var svg = d3.select("#emotion_chart").append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
    svg.selectAll("rect").
    data(emotionData).
    enter().
    append("svg:rect").
    attr("x", function(datum, index) { return x(index); }).
    attr("y", function(datum) { return height - y(datum.value); }).
    attr("height", function(datum) { return y(datum.value); }).
    attr("width", barWidth).
    attr("fill", "#408EBA");
    svg.selectAll("text.labels").
    data(emotionData).
    enter().
    append("svg:text").
    attr("x", function(datum, index) { return x(index) + barWidth; }).
    attr("y", function(datum) { return height - y(datum.value); }).
    attr("dx", -barWidth/2).
    attr("dy", "1.2em").
    attr("text-anchor", "middle").
    text(function(datum) { return datum.value;}).
    attr("fill", "white").
    attr("class", "labels");
    svg.selectAll("text.yAxis").
    data(emotionData).
    enter().append("svg:text").
    attr("x", function(datum, index) { return x(index) + barWidth; }).
    attr("y", height).
    attr("dx", -barWidth/2).
    attr("text-anchor", "middle").
    attr("style", "font-size: 12").
    text(function(datum) { return datum.emotion;}).
    attr("transform", "translate(0, 18)").
    attr("class", "yAxis");




    if(hasUserMedia()) {
        //alert(navigator.mozGetUserMedia)
        navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
        var streaming = false;
        navigator.getUserMedia({
            video: true,//开启视频
            audio: false//先关闭音频，因为会有回响，以后两台电脑通信不会有响声
        }, function (stream) {//将视频流交给video
            video.src = window.URL.createObjectURL(stream);
            streaming = true;
        }, function (err) {
            console.log("capturing", err);
        });
    }



    stats = new Stats();
    stats.domElement.style.position = 'absolute';
    stats.domElement.style.top = '0px';


    document.querySelector("#upload").addEventListener("click",function(event) {
        btn_upload();
    })

    document.addEventListener('clmtrackrIteration', function(event) {
        stats.update();
    }, false);

</script>
</body>
</html>
