$(function(){
    // 1： 初始化菜单导航
    ksdAside.init()
    // 2: 页面加载提示
    ksdLoading.init();
    // $("#layui-layer-iframe5").remove();

})


// 控制左侧菜单导航
var ksdAside = {
    init:function(){
        // 控制菜单的折叠展开的事件
        this.animate();
        // 初始化菜单的事件
        this.menuevent();
    },
    animate:function(){
        // 点击菜单绑定点击事件，然后控制自己下方的菜单进行展开和收起
        $("#asideapp").find(".byte-menu-inline-header").on("click",function(){
            // 这里可以控制排它
            //$(this).parents(".byte-menu-inline").siblings().find(".byte-menu-inline-content").hide();
            //$(this).parents(".byte-menu-inline").siblings().find(".byte-menu-icon-suffix").removeClass("is-open");

            $(this).next().toggle();
            $(this).find(".byte-menu-icon-suffix").toggleClass("is-open");
        })
    },
    // 2:控制菜单点击渲染右侧模板的功能
    menuevent:function() {
        $("#asideapp").find(".byte-menu-item").on("click", function () {
            // 获取每次点击的用户跳转连接地址
            var href = $(this).data("href");
            // alert(href);
            $.get(href,function(res){
                // console.log(res)
                $("#ksd-mainbox").html(res);
            })
            // $("#ksd-mainbox").load(href);
        })
        // 触发第一个元素，作为默认的激活项目
        $("#asideapp").find(".byte-menu-item").eq(0).trigger("click");
    }
}


// 页面加载提示
var ksdLoading = {
    init:function(){
        this.animate();
    },
    animate:function(){

    }
}

