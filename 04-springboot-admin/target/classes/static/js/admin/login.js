
var KungLogin = {
    tip: function (msg) {
        $("#kuangmessage").show().find(".msg").html(msg);
        // 这个代码意思是：把上一次的定时器关闭，在去开新的。否则不关就会一直闪烁
        if (this.stimer) clearTimeout(this.stimer);
        this.stimer = setTimeout(function () {
            $("#kuangmessage").hide();
        }, 3000);
    },

    login: function () {
        //给登录按钮绑定事件
        $("#kuang-tologin").on("click", function () {
            // 拿到登录用户名

            var username = $("#nickname").val();
            // 拿到用户密码
            var password = $("#password").val();
            console.log(username, password)
            if (!username) {
                KungLogin.tip("你踏马账号不填登录个锤子！臭弟弟");
                return;
            }

            if (!password) {
                KungLogin.tip("你踏马密码不填登录个锤子！臭弟弟");
                return;
            }

            //  异步请求到后台
            $.post("/admin/toLogin", {"nickname": username, "password": password}, function (res) {

                // 状态402 用户密码不正确
                if (res.code == 402) {
                    $("#nickname").select();
                    $("#password").val("");
                    KungLogin.tip(res.message);
                    // 状态403 密码不正确
                } else if (res.code == 403) {
                    $("#password").val("").select();
                    KungLogin.tip(res.message);
                    //	状态500 服务器错误
                } else if (res.code == 500) {
                    KungLogin.tip(res.message);
                } else if (res.code == 702) {
                    KungLogin.tip(res.message);
                } else if (res.code == 701) {
                    KungLogin.tip(res.message);
                } else {
                    window.location = "/admin/index";

                }
            })

        })
    }

}

$(function (event) {
    //初始化登录

    KungLogin.login();
    EnterCheck();
})

// 热键检查
var EnterCheck = function () {

    // 判断是否回车键 如果是登录
    window.addEventListener("keydown", function (event) {
        console.log(event.key);

        if (event.key == "Enter") {
            KungLogin.login();
            $("#kuang-tologin").click();

        }

        if (event.defaultPrevented) {
            return; // 如果已取消默认操作，则不应执行任何操作
        }

        var handled = false;
        if (event.key !== undefined) {
            // 使用KeyboardEvent.key处理事件，并将handled设置为true。
        } else if (event.keyCode !== undefined) {
            //使用KeyboardEvent.keyCode处理事件并将handled设置为true。
        }

        if (handled) {
            // 如果事件已处理，则禁止“双重操作”
            event.preventDefault();
        }
    }, true);


}
