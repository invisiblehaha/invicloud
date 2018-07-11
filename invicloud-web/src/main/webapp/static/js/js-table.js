$(function () {
    $('#editInfo').click(
        function () {
            $('#login').removeAttr("readonly");
            $('#real').removeAttr("readonly");
            $('#sex').removeAttr("readonly");
            $('#age').removeAttr("readonly");
            $('#tel').removeAttr("readonly");
        });
});

$(function () {
    $('#sureEdit'). click(
        function (){
            $('#login').attr("readonly","readonly");
            $('#real').attr("readonly","readonly");
            $('#sex').attr("readonly","readonly");
            $('#age').attr("readonly","readonly");
            $('#tel').attr("readonly","readonly");
            $.ajax({
                type: 'post',
                url: baselocation + 'edit',
                data:$("#updateForm").serialize(),
                dataType:'json',
                success : function(data) {
                    console.info(data);
                    if (data.code === 1) {
                        window.location.href = baselocation + 'view';
                    }
                    else alert('没输对吧，这里的具体错误实现过下写');
                }

            });
        });
});