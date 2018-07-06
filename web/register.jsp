<%--
  Created by IntelliJ IDEA.
  User: 木水
  Date: 2018/7/4
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta name="referrer" content="never">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script lanage="javascript">
        function changeImg(){
            var file = $("#form1").find("input")[4].files[0];
            var reader = new FileReader();
            var imgFile;
            reader.onload=function(e) {
               // alert('文件读取完成');
                imgFile = e.target.result;
                console.log(imgFile);
               // alert(imgFile);
                $("#imgid").attr('src', imgFile);
            };
            reader.readAsDataURL(file);
        }

        function checkUser(){
            var username = document.getElementById("usernameid").value;
            var password1 = document.getElementById("password1id").value;
            var password2 = document.getElementById("password2id").value;

            if(username == ""  ){
                alert("用户名不能为空");
                return false;
            }
            if(password1 == "" || password2==""  ){
                alert("密码不能为空");
                return false;
            }
            if(password1 != password2  ){
                alert("两次密码输入不一致");
                return false;
            }
            //document.getElementById("form1").submit();

            var info={
                username:$("#usernameid").val(),
                password:$("#password1id").val(),
                phone:$("#phoneid").val(),
                img:$("#imgid").attr("src")
            }
            //alert($("#imgid").attr("src"));
            $.ajax({
                url : "RegisterServlet",
                type : 'post',
                data: info ,
                success : function(data) {
                    alert("success");
                },
                error : function(error) {
                    alert("failed");
                }
            });
        }
    </script>
</head>
<body>
<canvas id="myCanvas" style="position: absolute;left:35%;"></canvas>
<div id="register_frame">
    <div id="r_head">
        <p>InvisiCloud</p>
    </div>
    <div id="r_body">
        <form id="form1" method="post" action="" enctype="multipart/form- data">
            <input id="usernameid" class="r_input" type="text" name="username" value="设置会员名" /><br><br>
            <input id="password1id" class="r_input" type="text" name="password1"  value="请设置你的密码"/><br><br>
            <input id="password2id" class="r_input" type="text" name="password2"  value="请再次输入密码"/><br><br>
            <input id="phoneid" class="r_input" type="text" name="phone" value="请输入手机号码"/><br><br>
            <input id="imgfileid" class="r_input" type="file" name="imgfile" onchange="changeImg()" /><br><br>
            <img id="imgid" height="200" width="200" src="1.jpg" alt="请选择图片"   />
            <input id="checkboxid" type="checkbox" checked="true" value="我已阅读***"/>
            <input type="button" value="提交" class="r_input" onclick = "checkUser();" />
        </form>
    </div>
    <div id="r_right">
        <li id="r_p1">5-25个字符,推荐使用中文</li>
        <li id="r_p2">不能和会员名相重复,6-20个字符,需要同时包含数字与字母</li>
        <li id="r_p3">请再次输入你的密码</li>
    </div>
</div>
</body>
</html>
