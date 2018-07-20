<%--
  Created by IntelliJ IDEA.
  User: 贺俞凯
  Date: 2018/7/10
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/base.jsp" %>
<html>
<head>
    <title>invicloud顾客注册</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/clmtrackr.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/Stats.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/cssReg.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class ="two">

    <div class="out-container">
        <div class="logo1"><h1>InvisiCLOUD</h1></div>
        <div class="container">
            <div class="web_qr_login" id="web_qr_login" style="display:block;">
                <div class="web_login" id="web_login">
                    <div class="login-box">
                        <div class="register_form">
                            <form id="registerForm">

                                <table>
                                    <tr>
                                        <td>
                                            <div class="uinArea" id="uinArea">
                                                <label class="input-tips" for="userName">姓名： </label>
                                                <div class="inputOuter" id="uArea">
                                                    <input class="inputstyle" type="text" name="userName" id="userName">
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <div class="numberArea" id="numberArea">
                                                <label class="input-tips" for="phoneNumber">电话： </label>
                                                <div class="inputOuter" id="nArea">
                                                    <input class="inputstyle" type="text" name="phoneNumber", id="phoneNumber">
                                                </div>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <div>
                                                <label class="input-tips">性别： </label><br>
                                                <input type="radio" name="sex" value="0"/>保密
                                                <input type="radio" name="sex" value="1" checked="checked"/>男
                                                <input type="radio" name="sex" value ="2"/>女
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <div>
                                                <label class="input-tips">出生年：</label><br>
                                                <select id="birthYear"></select>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <div>
                                                <br>
                                                <div class="col-md-3 col-sm-3 col-xs-6">
                                                    <a href="#" class="btn btn-sm animated-button victoria-one" onclick="openCatagory();end_Camera();" style="float:left">打开相册</a>
                                                </div><br><br>

                                                <div class="col-md-3 col-sm-3 col-xs-6">
                                                    <a href="#" class="btn btn-sm animated-button victoria-two" onclick="closeCatagory();" style="float:left">关闭相册</a>
                                                </div><br><br><br><br>

                                                <div id="catagory" style="position: absolute;left: 420px;top:0px;display: none">
                                                    <input id="imgfile" type="file" name="userUploadImg" onchange="changeImg()"/><br><br><br><br>
                                                    <img id="imgid" width="300" src="${pageContext.request.contextPath}/static/images/initial.jpg" alt="userUploadImg"/>
                                                    <br><br><br><br>
                                                </div>

                                                <div class="col-md-3 col-sm-3 col-xs-6">
                                                    <a href="#" class="btn btn-sm animated-button victoria-three" onclick="start_Camera();closeCatagory();" style="float:left">打开摄像头</a>
                                                </div><br><br>

                                                <div class="col-md-3 col-sm-3 col-xs-6">
                                                    <a href="#" class="btn btn-sm animated-button victoria-four" onclick="end_Camera();" style="float:left">关闭摄像头</a>
                                                </div><br><br><br><br>

                                                <div id="video-canvas" style="display: none">
                                                    <video  id="myVideo" autoplay="autoplay" height="350" width="350" style="border: 1px solid gray; position: absolute;left: 420px;top:0px; object-fit: fill"></video><hr/>
                                                    <canvas id="myCanvasForVideo" height="350" width="350" style="border: 1px solid gray; position: absolute;left: 420px;top:0px;"></canvas>
                                                    <canvas id="myCanvas" height="350" width="350" style="border: 1px solid gray;position: absolute;left: 420px;top:410px;"></canvas>
                                                </div>

                                            </div>
                                        </td>
                                    </tr>
                                </table>

                                <div>
                                    <a href="#" class="button is-silver is-horizontal" onclick="checkUser();" style="position: absolute;left: 50px;top:540px;">
                                        <span class="shadow"></span>
                                        <span class="wrapper">
                <span class="bg"></span>
                <span class="b-title">REGISTER</span>
                <span class="bg"></span>
               </span>
                                    </a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>



<script language="javascript" type="text/javascript">
    var img1;
    var cameraInvoked = false;
    var streaming = false;
    var video = document.getElementById('myVideo');

    function hasUserMedia(){//判断是否支持调用设备api，因为浏览器不同所以判断方式不同哦
        return !!(navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);
    }

    window.onload=function()
    {
        var myDate = new Date();
        var startYear = myDate.getFullYear()-80;//起始年份，允许80岁以内的人注册
        var endYear = myDate.getFullYear();//当前年份
        var obj= document.getElementById('birthYear');
        for(var i=startYear;i<=endYear;i++)
        {
            obj.options.add(new Option(i,i))
        }
        obj.options[obj.options.length-81].selected = 1;

        /*//tracking.js face tracking: dropped because of its constancy
        //tracking face
        var canvas = document.getElementById('myCanvasForVideo');
        var context = canvas.getContext('2d');

        var videoTracker = new tracking.ObjectTracker(['face']);
        videoTracker.setInitialScale(4);
        videoTracker.setStepSize(1.7);
        videoTracker.setEdgesDensity(0.1);
        tracking.track('#myVideo',videoTracker);


        videoTracker.on('track',function(event){
            canvas.width = canvas.width;
            event.data.forEach(function(rect) {
                context.strokeStyle = '#c0c0c0';
                context.moveTo(rect.x,rect.y+10);
                context.lineTo(rect.x,rect.y);
                context.lineTo(rect.x+rect.width,rect.y);
                context.lineTo(rect.x+rect.width, rect.y+10);//上半部分
                context.moveTo(rect.x,rect.y+rect.height-10);
                context.lineTo(rect.x,rect.y+rect.height);
                context.lineTo(rect.x+rect.width,rect.y+rect.height);
                context.lineTo(rect.x+rect.width,rect.y+rect.height-10);
                context.stroke();//画线
                context.font = '11px Helvetica';
                context.fillStyle = "#fff";
                context.fillText('x: ' + rect.x + 'px', rect.x + rect.width + 5, rect.y + 11);
                context.fillText('y: ' + rect.y + 'px', rect.x + rect.width + 5, rect.y + 22);
            });

            var canvas2 = document.getElementById('myCanvas');
            var context2 = canvas2.getContext('2d');
            img1 = canvas2.toDataURL("image/png");
            context2.fillStyle="#ffffff";
            context2.beginPath();
            context2.fillRect(0,0,canvas2.width,canvas2.height);
            context2.closePath();
            if(event.data.length != 0 )
            {
                context2.drawImage(video,0,0,400,400);

                UserUpload = true;//放到search里面没什么用
            }


        });*/
    };

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
            context2.drawImage(video,0,0,350,350);
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





    function changeImg() {
        cameraInvoked = false;
        var file=$("#registerForm").find("input")[5].files[0];
        var reader = new FileReader();
        var imgFile;
        reader.onload=function(e) {
            imgFile = e.target.result;
            console.log(imgFile);
            $("#imgid").attr('src', imgFile);
        };
        reader.readAsDataURL(file);

    }



    function start_Camera() {

        cameraInvoked = true;
        if(document.getElementById("video-canvas").style.display=="none")
        {
            document.getElementById("video-canvas").style.display="";
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
        cameraInvoked = false;
    }


    function openCatagory() {

        if (document.getElementById("catagory").style.display == "none") {
            document.getElementById("catagory").style.display = "";
        }
        cameraInvoked = false;
    }
    function closeCatagory() {

        if (document.getElementById("catagory").style.display == "") {
            document.getElementById("catagory").style.display = "none";
        }
    }
    function checkUser() {
        var userName = document.getElementById("userName").value;
        var phoneNumber = document.getElementById("phoneNumber").value;

        var obj=document.getElementById("birthYear");
        var index=obj.selectedIndex;
        var valOfYear = obj.options[index].value;//获取birthYear里用户选择的年份

        if(userName == "")
        {
            alert("用户名不能为空");
            return false;
        }
        if(phoneNumber =="")
        {
            alert("请输入联系方式");
            return false;
        }


        if(cameraInvoked==true)
        {
            var info =
                {
                    userName:$("#userName").val(),
                    sex:$("input[name='sex']:checked").val(),
                    birthYear:valOfYear,
                    phoneNumber:$("#phoneNumber").val(),
                    img:img1
                }
        }
        else
        {
            var info=
                {
                    userName:$("#userName").val(),
                    sex:$("input[name='sex']:checked").val(),
                    birthYear:valOfYear,
                    phoneNumber:$("#phoneNumber").val(),
                    img:$("#imgid").attr("src")
                }
        }
        $.ajax({
            url : '${pageContext.request.contextPath}/register/register/register',
            type:'post',
            data:info,
            dataType:'json',
            success : function(data) {
                var return_type = data.message;
                if(data.code == 1)//for success
                {
                    alert("success");
                }
                else//for common return type
                {
                    alert(return_type);
                }
            },
            error : function(error) {
                alert("failed");
            }
        });
    }


</script>
</html>
