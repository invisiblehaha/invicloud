
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/base.jsp" %>
<html>
<head>
    <title>invicloud注册页面</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <style>
        video,canvas{
            border:1px solid gray;
            width:400px;
            height:400px;
        }
    </style>
    <style>
        body{
            text-align:center;
            margin-left:auto;
            margin-right:auto;
        }
    </style>
</head>
<body>
<form id="registerForm">
    <div>
        <label for="userName">Enter user name: </label>
        <input type="text" name="userName" id="userName">
    </div>
    <div>
        <label for="userPassword">Enter your password: </label>
        <input type="text" name="userPassword" id="userPassword">
    </div>
    <div>
        <label for="userPasswordConfirm">Confirm your password: </label>
        <input type="text" name="userPasswordConfirm" id="userPasswordConfirm">
    </div>
    <div>
        <li>Choose your sex: </li>
        <input type="radio" name="sex" value="0"/>保密
        <input type="radio" name="sex" value="1" checked="checked"/>男
        <input type="radio" name="sex" value ="2"/>女
    </div>
    <div>
        <li>Choose your birth year: </li>
        <select id="birthYear"></select>
    </div>
    <div>
        <label for="phoneNumber">Enter your phone number: </label>
        <input type="text" name="phoneNumber", id="phoneNumber">
    </div>
    <div>
        <button type="button" id="btn_submit" onclick="checkUser();">Submit</button>
    </div>
    <div>
        <input id="imgfile" type="file" name="userUploadImg" onchange="changeImg()"/>
        <img id="imgid" height="200"  src="${pageContext.request.contextPath}/static/images/initial.jpg" alt="userUploadImg"/>
        <br><br><br><br><br><br>
        <button type="button" id="startCamera" onclick="start_Camera();">打开摄像头</button>
        <%--<button type="button" id="endCamera" onclick="end_Camera();">关闭摄像头</button>--%>
        <video autoplay></video>
        <canvas id="myCanvas" ></canvas>
        <button type="button" id="capture">拍照</button>
    </div>




</form>

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
        var file=$("#registerForm").find("input")[7].files[0];
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
        //关不掉，不会了
    }


    function checkUser() {
        var userName = document.getElementById("userName").value;
        var password = document.getElementById("userPassword").value;
        var passwordConfirm = document.getElementById("userPasswordConfirm").value;
        var phoneNumber = document.getElementById("phoneNumber").value;

        var obj=document.getElementById("birthYear");
        var index=obj.selectedIndex;
        var valOfYear = obj.options[index].value;//获取birthYear里用户选择的年份

        if(userName == "")
        {
            alert("用户名不能为空");
            return false;
        }
        if(password ==""||passwordConfirm=="")
        {
            alert("密码不能为空");
            return false;
        }
        if(passwordConfirm!=password)
        {
            alert("两次密码输入不一致");
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
                    password:$("#userPassword").val(),
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
                password:$("#userPassword").val(),
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
