$(function(){
    $('#btn_submit').click(function(){
        if(cameraInvoked == true)
        {
            var info = {
                imgString:img1
            }
        }
        else
        {
            var info = {
                loginName: $("#loginName").val(),
                loginPassword:$("#loginPassword").val(),
                registerCode:$("#registerCode").val()
            }
        }
        $.ajax({
            type:'post',
            url:baselocation+'/login',
            data:info,
            dataType:'json',
            success : function(data) {
                console.info(data);
                if (data.code === 1) {
                    window.location.href = baselocation + '/index';
                }
                else alert('error: please make sure your password is correct if you log in by password ' +'\n'+
                    'and only one person is in camera if you log in by camera');
            }
        });
    });
});
$(function() {
    $('#registerPic').click(function() {
        $(this).attr('src', baselocation + '/captcha-image.jpg?' + Math.floor(Math.random() * 100));
    })
});
