<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/base.jsp" %>
<html>
<head>
    <title>invicloud登陆页面</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/clmtrackr.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/Stats.js"></script>
    <script type="text/javascript">baselocation="${ctx}"</script>
</head>

<body>
<form id="loginForm">

    <div id="passwordLogin" style="position: absolute; left:100px; top:100px; display:block;">
        <div>
            <label for="loginName">Enter name: </label>
            <input type="text" name="loginName" id="loginName" required>
        </div>
        <div>
            <label for="loginPassword">Enter password: </label>
            <input type="text" name="loginPassword" id="loginPassword" required>
        </div>
        <div>
            <label for="registerCode">Enter registercode: </label>
            <input type="text" name="registerCode" id="registerCode" required>
            <img id="registerPic" src="${ctx}\captcha-image.jpg" alt="registerCode"/>
        </div>
    </div>

    <div>
        <Button type="button" id="btn_submit" style="position: absolute;left:100px;top:520px">Submit!</Button>
    </div>

    <div>
        <button type="button"  onclick="start_Camera();" style="position: absolute;left: 100px; top:50px;">打开摄像头</button>
    </div>
    <div>
        <button type="button" onclick="end_Camera();" style="position: absolute;left: 200px; top:50px;">关闭摄像头</button>
    </div>
    <div id="video-canvas" style="position: absolute; left: 100px; top:100px;display: none">
        <video  id="myVideo" autoplay="autoplay" height="400" width="400" style="border: 1px solid gray; position: absolute;left: 100px;top:100px; object-fit: fill"></video><hr/>
        <canvas id="myCanvasForVideo" height="400" width="400" style="border: 1px solid gray; position: absolute;left: 100px;top:100px;"></canvas>
        <canvas id="myCanvas" height="400" width="400" style="border: 1px solid gray;position: absolute;left: 520px;top:100px;"></canvas>
    </div>
</form>

</body>
<script type="text/javascript" src="${ctxsta}/js/login.js"></script>
<script language="JavaScript" type="text/javascript">
    var img1;
    var cameraInvoked = false;
    var streaming = false;
    var video = document.getElementById('myVideo');


    function hasUserMedia(){//判断是否支持调用设备api，因为浏览器不同所以判断方式不同哦
        return !!(navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
    }

    //tracking
    var ctracker = new clm.tracker();
    var canvas2 = document.getElementById('myCanvas');
    var context2 = canvas2.getContext('2d');
    ctracker.init();
    var positionInitial = ctracker.getCurrentPosition();//获取最初的position，用作后续的判断(放到了detect检测人脸里)
    ctracker.start(video);
    function positionLoop() {
        requestAnimationFrame(positionLoop);

        var positions = ctracker.getCurrentPosition();
        img1 = canvas2.toDataURL("image/png");//储存了img流，直接用这个的base64就行
        context2.fillStyle="#ffffff";
        context2.beginPath();
        context2.fillRect(0,0,canvas2.width,canvas2.height);
        context2.closePath();

        if(positions!= positionInitial)//如果video里有人脸
        {
            //画出摄像头捕捉的图像
            context2.drawImage(video,0,0,400,400);
            //将获取的图片base64信息封装在info中
            UserUpload = true;
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

    function start_Camera() {

        cameraInvoked = true;
        if(document.getElementById("video-canvas").style.display=="none")
        {
            document.getElementById("video-canvas").style.display="";
        }
        if(document.getElementById("passwordLogin").style.display == "block")
        {
            document.getElementById("passwordLogin").style.display = "none";
        }

        if (hasUserMedia()) {
            //alert(navigator.mozGetUserMedia)
            navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;

            navigator.getUserMedia({
                video: true,//开启视频
                audio: false//先关闭音频，因为会有回响，以后两台电脑通信不会有响声
            }, function (stream) {//将视频流交给video
                video.src = window.URL.createObjectURL(stream);
                streaming = true;
                video.play();
            }, function (err) {
                console.log("capturing", err)
            });
        }
    }


    function end_Camera()
    {
        if(document.getElementById("video-canvas").style.display=="")
        {
            document.getElementById("video-canvas").style.display="none";
        }
        if(document.getElementById("passwordLogin").style.display == "none")
        {
            document.getElementById("passwordLogin").style.display = "block";
        }
        cameraInvoked = false;
    }

</script>
</html>
