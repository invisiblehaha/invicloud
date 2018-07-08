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
                    window.location.href = baselocation + '/product/category/view';
                }
                else alert('没输对吧，这里的具体错误实现过下写');
            }
        });
    });
});
$(function() {
    $('#registerPic').click(function() {
        $(this).attr('src', baselocation + '/captcha-image.jpg?' + Math.floor(Math.random() * 100));
    })
});