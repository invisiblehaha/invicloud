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
        #imgid{
            border:1px solid gray;
            width:400px;
            height:400px;
            position: relative;
            top:-200px;
            left:0;
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
        function changeImg(){
            var file = $("#form1").find("input")[0].files[0];
            var reader = new FileReader();
            var imgFile;
            reader.onload=function(e) {
                // alert('文件读取完成');
                imgFile = e.target.result;
                console.log(imgFile);
                // alert(imgFile);
                $("#imgid").attr('src', imgFile);
                info={imgString:imgFile};
            };
            reader.readAsDataURL(file);
        }

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
                alert("发送成功!");
            } else {
                alert("连接失败!");
            }
        }
        window.close = function () {
            websocket.onclose();
        }
    </script>
</head>

<body>
<div id="upper">
    <video autoplay></video>
    <canvas id="myCanvas"></canvas>
    <img id="imgid" height="200" width="200" src="" alt="请选择图片"   />
    <form id="form1">
        <button type="button" id="capture">拍照上传</button>
        <input type="file" name="file" id="picture" value="选择图片" onchange="changeImg()" >
        <button type="button" id="upload" >确认上传</button>
    </form>
</div>
<div id="lower">
</div>

<script type="text/javascript">
    function hasUserMedia(){//判断是否支持调用设备api，因为浏览器不同所以判断方式不同
        return !!(navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
    }
    if(hasUserMedia()){
        //alert(navigator.mozGetUserMedia)
        navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;
        var video=document.querySelector("video");
        var canvas=document.querySelector("canvas");
        var streaming = false;
        navigator.getUserMedia({
            video:true,//开启视频
            audio:false//先关闭音频，因为会有回响，以后两台电脑通信不会有响声
        },function(stream){//将视频流交给video
            video.src=window.URL.createObjectURL(stream);
            streaming = true;
        },function(err){
            console.log("capturing",err);
        });

        var info={};
        var context = canvas.getContext('2d');
        //为拍照上传按钮添加监听
        document.querySelector("#capture").addEventListener("click",function(event)  {
            if(streaming){
                context.fillStyle="#ffffff";
                context.beginPath();
                context.fillRect(0,0,canvas.width,canvas.height);
                context.closePath();
                //画出摄像头捕捉的图像
                context.drawImage(video,0,0,300,150);
                var img=canvas.toDataURL("image/png");
                //将获取的图片base64信息封装在info中
                info = {
                    imgString: canvas.toDataURL("image/png")
                }
                console.log(info.imgString);
                $("#imgid").attr('src',img);
            }
        });
    }else{
        alert("浏览器暂不支持")
    }
    //为图片上传按钮添加监听
    document.querySelector("#upload").addEventListener("click",function(event) {
        btn_upload();
    })
</script>

</body>
</html>