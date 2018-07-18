$(function(){
    $('#btn_submit').click(function(){
        if($('#loginName').val().length<=0){
            $('#loginName').focus();
            $('#message').html('请输入账号');
            return false;
        }
        if($('#loginPassword').val().length<=0){
            $('#loginPassword').focus();
            $('#message').html('请输入密码');
            return false;
        }if($('#registerCode').val().length<=0){
            $('#registerCode').focus();
            $('#message').html('请输入验证码');
            return false;
        }
        $.ajax({
            type:'post',
            url:baselocation+'/login',
            data:$("#loginForm").serialize(),
            dataType:'json',
            success : function(data) {
                console.info(data);
                if (data.code === 1) {
                    window.location.href = baselocation + '/index';
                }
                else {
                    $('#message').html(data.message);
                }
            }
        });
    });
});
$(function() {
    $('#registerPic').click(function() {
        $(this).attr('src', baselocation + '/captcha-image.jpg?' + Math.floor(Math.random() * 100));
    })
});
$(document).keyup(function(event){
    if(event.keyCode==13){
        $('#btn_submit').click();
    }
})