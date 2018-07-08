$(function(){
    $('#btn_submit').click(function(){
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
            }
        });
    });
});
$(function() {
    $('#registerPic').click(function() {
        $(this).attr('src', baselocation + '/captcha-image.jpg?' + Math.floor(Math.random() * 100));
    })
});