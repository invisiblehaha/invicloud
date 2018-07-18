<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/base.jsp" %>
<html>
<head>
    <title>invicloud注册页面</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <link href="${pageContext.request.contextPath}/static/css/cssReg.css" rel="stylesheet" type="text/css" />
    <%--
        <link href="${pageContext.request.contextPath}/static/css/css-table.css" rel="stylesheet" type="text/css" />
    --%>

    <style>
        video,canvas{
            border:1px solid gray;
            width:300px;
            height:300px;
        }
    </style>
    <%--<style>
        body{
            text-align:center;
            margin-left:auto;
            margin-right:auto;
        }
    </style>--%>
</head>
<body>

<div class ="two">

    <div class="out-container">
        <div class="logo2"><h1>InvisiCLOUD</h1></div>
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
                                                <%-- <button type="button" id="open_Catagory" onclick="openCatagory();end_Camera();" class="button_blue">打开相册</button><br><br>--%>
                                                <%--<button type="button" id="close_Catagory" onclick="closeCatagory();" class="button_blue">关闭相册</button><br><br><br><br>--%>

                                                <div id="catagory" style="display: none">
                                                    <input id="imgfile" type="file" name="userUploadImg" onchange="changeImg()"/><br><br><br><br>
                                                    <img id="imgid" width="300" src="${pageContext.request.contextPath}/static/images/initial.jpg" alt="userUploadImg"/>
                                                    <br><br><br><br>
                                                </div>

                                                <div class="col-md-3 col-sm-3 col-xs-6">
                                                    <a href="#" class="btn btn-sm animated-button victoria-three" onclick="start_Camera();closeCatagory();" style="float:left">打开摄像头</a>
                                                </div><br><br>
                                                <%--<button type="button" id="startCamera" onclick="start_Camera();closeCatagory();" class="button_blue">打开摄像头</button><br><br>--%>
                                                <%-- <button type="button" id="endCamera" onclick="end_Camera();" class="button_blue">关闭摄像头</button><br><br><br><br>--%>

                                                <div class="col-md-3 col-sm-3 col-xs-6">
                                                    <a href="#" class="btn btn-sm animated-button victoria-four" onclick="end_Camera();" style="float:left">关闭摄像头</a>
                                                </div><br><br><br><br>

                                                <div id="video-canvas" style="display: none">
                                                    <video  autoplay="autoplay"></video><hr/>
                                                    <button type="button" id="capture">拍照</button><br>
                                                    <canvas id="myCanvas"></canvas>
                                                </div>

                                            </div>
                                        </td>
                                    </tr>
                                </table>
                                <div >
                                    <button type="button" id="btn_submit" onclick="checkUser();" class="button_flip"  data-title="Submit">Submit！</button>
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
    };
    function changeImg() {
        UserUpload = false;
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


    var streaming = false;
    var mediaStreamTrack;
    var video = document.querySelector("video");
    function start_Camera() {

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
    var img1;
    var UserUpload = false;
    document.querySelector("#capture").addEventListener("click", function (event) {
        if (streaming) {
            var canvas = document.querySelector("canvas");
            img1 = canvas.toDataURL("image/png");
            var context = canvas.getContext('2d');
            context.fillStyle="#ffffff";
            context.beginPath();
            context.fillRect(0,0,canvas.width,canvas.height);
            context.closePath();

            //画出摄像头捕捉的图像
            context.drawImage(video, 0, 0,300,150);
            //将获取的图片base64信息封装在info中
            UserUpload = true;
        }
    })

    function end_Camera()
    {
        if(document.getElementById("video-canvas").style.display=="")
        {
            document.getElementById("video-canvas").style.display="none";
        }
    }


    function openCatagory() {

        if (document.getElementById("catagory").style.display == "none") {
            document.getElementById("catagory").style.display = "";
        }
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


        if(UserUpload)
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