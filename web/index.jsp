<%--
  Created by IntelliJ IDEA.
  User: 木水
  Date: 2018/7/3
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>capture</title>
  <style>
    video,canvas{
      border:1px solid gray;
      width:400px;
      height:400px;
    }
  </style>
</head>
<body>
<video autoplay style="position: absolute;display: none;"></video>
<canvas id="myCanvas" style="position: absolute;left:35%;"></canvas>

<!--   <input type="file" name="file"  style="position: absolute;left: 30%;top: 35%" >
  <button style="position: absolute;left: 30%;top: 45%">图片上传</button>
-->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

    function hasUserMedia(){//判断是否支持调用设备api，因为浏览器不同所以判断方式不同哦
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
       window.setInterval(function(event){
            if(streaming){
                //alert(video.clientHeight)
                //canvas.width = video.clientWidth;
                //canvas.height= video.clientHeight;
                canvas.width = 600;
                canvas.height = 600;
                var context = canvas.getContext('2d');
                imgString = canvas.toDataURL("image/png");

                context.drawImage(video,20,20);
                console.log(imgString);
                var info = {
                    imgString: canvas.toDataURL("image/png")
                }

                $.ajax({
                    url : "FaceServlet",
                    type : 'post',
                    data:info,
                    success : function(data) {
                       //alert("success");
                    },
                    error : function(error) {
                        alert("failed");
                    }
                });
            }
        },5000)
    }else{
        alert("浏览器暂不支持")
    }
</script>

</body>
</html>