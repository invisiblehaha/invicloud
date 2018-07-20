$(function () {
    $(".treeview").click(function(){
        document.cookie='selectedmenu='+$(this).index()+';path=/';
    });
})