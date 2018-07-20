<%--
  Created by IntelliJ IDEA.
  User: 木水
  Date: 2018/7/13
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
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
<!DOCTYPE HTML>
<html>
<head>

    <link rel="stylesheet" href="${ctxsta}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxsta}/bootstrap-table/dist/bootstrap-table.min.css"/>
    <style>
        #emotion_container {
            width: 600px;
        }
        #emotion_icons {
            height: 50px;
            padding-left: 40px;
        }
        .emotion_icon {
            width : 40px;
            height : 40px;
            margin-top: 5px;
            /*margin-left : 13px;*/
            margin-left: 35px;
        }
        #emotion_chart, #emotion_icons {
            margin: 0 auto;
            width : 400px;
        }
        #icon1, #icon2, #icon3, #icon4 {
            visibility : hidden;
        }
        /* d3 */
        .bar {
            fill : steelblue;
            fill-opacity : .9;
        }
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
        }
        #picture{
            text-align: center;
        }
    </style>

    <script>
        var img1;
        function btn_upload() {
            $.ajax({
                url : '${pageContext.request.contextPath}/detect/detect/',
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
                            alert("识别成功！"+cusInfo.rows[0].customerId+"号会员!")
                            doSendUsers("1 "+cusInfo.rows[0].customerId);
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
    <script type="text/javascript">
        var websocket=null;
        if('WebSocket' in window){
            websocket=new WebSocket("ws://<%=basePath%>/webSocketServer");
        }
        else if('MozWebSocket' in window){
            websocket=new MozWebSocket("ws://<%=basePath%>/webSocketServer");
        }
        else{
            websocket=new SockJS("http://<%=basePath%>/sockjs/webSocketServer");
        }
        websocket.onopen = onOpen;
        websocket.onmessage = onMessage;
        websocket.onerror = onError;
        websocket.onclose = onClose;
        function onOpen(openEvt) {
            alert(openEvt.Data);
        }
        function onMessage(evt) {
            alert(evt.data);
        }
        function onError() {
        }
        function onClose() {
        }
        //向服务器发送人脸识别成功会员的id
        function doSendUsers(msg) {
            if (websocket.readyState === websocket.OPEN) {
                //var msg = document.getElementById("inputMsg").value;
                websocket.send(msg);//调用后台handleTextMessage方法
            } else {
                console.log("failed");
            }
        }
        window.close = function () {
            websocket.onclose();
        }
    </script>
</head>

<body>
<div id="upper">
    <video  id="myVideo" autoplay="autoplay" height="400" width="400" style="border: 1px solid gray; position: absolute;left: 400px;top:100px; object-fit: fill"></video><hr/>
    <canvas id="myCanvasForVideo" height="400" width="400" style="border: 1px solid gray; position: absolute;left: 400px;top:100px;"></canvas>
    <canvas id="myCanvas" height="400" width="400" style="border: 1px solid gray; position: absolute;left: 820px;top:100px;"></canvas>
    <div id="emotion_container">
        <div id="emotion_icons"  style="position: absolute; left:575px; top:600px;">
            <img class="emotion_icon" id="icon1" src="${pageContext.request.contextPath}/static/images/icon_angry.png">
            <img class="emotion_icon" id="icon2" src="${pageContext.request.contextPath}/static/images/icon_sad.png">
            <img class="emotion_icon" id="icon3" src="${pageContext.request.contextPath}/static/images/icon_surprised.png">
            <img class="emotion_icon" id="icon4" src="${pageContext.request.contextPath}/static/images/icon_happy.png">
        </div>
        <div id='emotion_chart'  style="position: absolute; left:610px; top:660px;"></div>
    </div>
    <div id="controls">
    <button type="button" id="upload" style="position: absolute;left: 820px;top:550px;">分析</button>
    </div>
</div>
<div id="lower">
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

        var cp = ctracker.getCurrentParameters();
        var er = ec.meanPredict(cp);
        var emo = null;
        var angry;
        var sad;
        var surprised;
        var happy;
        if(er)
        {
            updateData(er);
            for(var i=0;i<er.length;i++)
            {
                if(i == 0)
                {
                    angry = er[0].value;
                }
                else if(i == 1)
                {
                    sad = er[1].value;
                }
                else if(i == 2)
                {
                    surprised = er[2].value;
                }
                else if(i == 3)
                {
                    happy = er[3].value;
                }
            }
            var max = Math.max(angry,sad,surprised,happy)
            if(angry > 0.4 && angry == max)
            {
                emo = 'angry';
                document.getElementById('icon1').style.visibility = 'visible';
            }else if(sad > 0.4 && sad == max)
            {
                emo = 'sad';
                document.getElementById('icon2').style.visibility = 'visible';
            }else if(surprised > 0.4 && surprised == max)
            {
                emo = 'surprised';
                document.getElementById('icon3').style.visibility = 'visible';
            }else if(happy > 0.4 && happy == max)
            {
                emo = 'happy';
                document.getElementById('icon4').style.visibility = 'visible';
            }

            else
            {
                emo = 'peaceful';
                document.getElementById('icon1').style.visibility = 'hidden';
                document.getElementById('icon2').style.visibility = 'hidden';
                document.getElementById('icon3').style.visibility = 'hidden';
                document.getElementById('icon4').style.visibility = 'hidden';
            }
            info['emotion']=emo;

            /*for(var i=0;i<er.length;i++)
            {
                if (er[i].value > 0.2) {
                    document.getElementById('icon'+(i+1)).style.visibility = 'visible';
                    if(i == 0)
                    {
                        emo = 'angry ';
                    }
                    else if(i == 1)
                    {
                        emo = 'sad ';
                    }
                    else if(i == 2)
                    {
                        emo = 'surprised ';
                    }
                    else if(i == 3)
                    {
                        emo = 'happy ';
                    }
                }
                else {
                    document.getElementById('icon'+(i+1)).style.visibility = 'hidden';
                    emo += 'peaceful ';
                }//某一行的情绪值大于0.4则将对应的icon置可见
                info['emotion'] = emo;
            }*/
        }
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
    attr("fill", "#2d578b");
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