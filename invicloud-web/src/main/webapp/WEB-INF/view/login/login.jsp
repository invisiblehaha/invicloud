<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/base.jsp" %>
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
    <title>invicloud登陆页面</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script type="text/javascript">baselocation = "${ctx}"</script>
    <link href="<%=basePath%>static/css/css1.css" rel="stylesheet" type="text/css"/>
</head>

<body>
<div class="two">
    <div class="outout"></div>
    <div class="outout">
        <div class="out-container">
            <div class="container">

                <div class="web_qr_login" id="web_qr_login" style="display: block;">
                    <div class="logo1"><h2 style="color:cornflowerblue;font-size: 2.5em">InvisiCLOUD</h2></div>
                    <!--登录-->
                    <div class="web_login" id="web_login">


                        <div class="login-box">
                            <div class="login_form">
                                <form id="loginForm">
                                    <table>
                                        <tr>
                                            <td>
                                                <div class="uinArea" id="uinArea">
                                                    <label class="input-tips" for="loginName">用户名: </label>
                                                    <div class="inputOuter" id="uArea">
                                                        <input class="inputstyle" type="text" name="loginName"
                                                               id="loginName" placeholder="请输入用户名" required>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="pwdArea" id="pwdArea">
                                                    <label class="input-tips" for="loginPassword">密码: </label>
                                                    <div class="inputOuter" id="pArea">
                                                        <input class="inputstyle" type="password" name="loginPassword"
                                                               id="loginPassword" placeholder="请输入密码" required>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="uinArea">
                                                    <label class="input-tips" for="registerCode">验证码: </label>
                                                    <div class="inputOuter">
                                                        <input class="inputstyle" type="text" name="registerCode"
                                                               id="registerCode" placeholder="请输入验证码" required>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="width: 100%;align-content: center">
                                                <span style='color: red;' id='message'></span>
                                                <img id="registerPic" src="${ctx}\captcha-image.jpg"
                                                     alt="registerCode" align="right" />
                                            </td>
                                        </tr>


                                    </table>
                                    <div style="margin-top: 10px;">
                                        <Button type="button" id="btn_submit" class="button_blue">登录</Button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${ctxsta}/js/login.js"></script>
</html>
