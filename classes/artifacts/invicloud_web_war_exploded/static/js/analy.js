$(function () {
    $('#analy').click(function () {
        $.ajax({
            type: 'post',
            url: baselocation + 'generate',

        });
    });

});
