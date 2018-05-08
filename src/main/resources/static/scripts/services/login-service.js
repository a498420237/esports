define(function (require, exports, module) {
    seajs.use(['jquery'], function ($) {

        exports.bindEvents = function () {
            bindGetCodeEvent();
            bindLoginEvent();
        };
        // 绑定获取验证码事件
        var InterValObj; //timer变量，控制时间
        var count = 90; //间隔函数，1秒执行
        var curCount;//当前剩余秒数
        // 绑定登录事件
        var isLogin = true;
        var bindLoginEvent = function () {
            $("#loginBtn").click(function () {
                var mobile = $("#phoneInput").val();
                if (mobile == "") {
                    layer.msg("请输入手机号");
                    return false;
                }
                if (!isPoneAvailable(mobile)) {
                    layer.msg("请输入正确的手机号");
                    return false;
                }
                var code = $("#codeInput").val();
                if (code == "") {
                    layer.msg("请输入验证码");
                    return false;
                }
                if (isLogin) {
                    isLogin = false;
                    login(mobile, code);
                    return false;
                }
            });
        };
        var bindGetCodeEvent = function () {
            $("#getCodeBtn").click(function () {
                var mobile = $("#phoneInput").val();
                if (mobile == "") {
                    layer.msg("请输入手机号");
                    return;
                }
                if (!isPoneAvailable(mobile)) {
                    layer.msg("请输入正确的手机号");
                    return;
                }

                sendsms(this,mobile);
            });
        };

        //发送验证码
        function sendsms(dom,mobile) {
            $.ajax({
                url: "/sendMobileCode",
                datatype: 'json',
                type: "post",
                data: {
                    "mobile": mobile
                }, success: function (obj) {

                    if (obj == null) {
                        layer.msg("远程接口调用失败");
                    } else {
                        if (obj.code == 200) {
                            layer.msg("验证码发送成功");
                            curCount = count;
                            $(dom).off("click");
                            $(dom).removeAttr("class", "bg-red");
                            $(dom).attr("class", "bg-999");
                            $(dom).text("倒计时" + curCount + "秒");
                            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次

                        } else {
                            //layer.msg（obj.msg);
                            layer.msg("验证码发送失败请重试！");
                        }
                    }
                }
            });
        }

        //登录方法
        function login(mobile, code) {
            $.ajax({
                url: "/login",
                datatype: 'json',
                type: "post",
                async: false,
                data: {
                    "mobile": mobile,
                    "code": code
                }, success: function (obj) {
                    if (typeof(obj) == "undefined") {
                        layer.msg("系统内部错误");
                    } else {
                        if (obj.code == 200) {
                            window.open('/user/index', '_self');
                        } else {
                            if (typeof(obj.msg) == "undefined") {
                                layer.msg("返回值错误");
                            } else {
                                layer.msg(obj.msg);
                            }
                        }
                    }
                    isLogin = true;
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg("ajax调用错误");
                }
            });
        }

        //timer处理函数
        function SetRemainTime() {
            if (curCount == 0) {
                window.clearInterval(InterValObj);//停止计时器
                $("#getCodeBtn").on("click", function () {
                    var mobile = $("#phoneInput").val();
                    if (mobile == "") {
                        layer.msg("请输入手机号");
                        return;
                    }
                    if (!isPoneAvailable(mobile)) {
                        layer.msg("请输入正确的手机号");
                        return;
                    }
                    curCount = count;
                    //设置button效果，开始计时
                    $(this).off("click");
                    $(this).removeAttr("class", "bg-red");
                    $(this).attr("class", "bg-999");
                    $(this).text("倒计时" + curCount + "秒");
                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                    sendsms(mobile);

                });
                $("#getCodeBtn").removeAttr("class", "bg-999");
                $("#getCodeBtn").attr("class", "bg-red");
                $("#getCodeBtn").text("发送验证码");

            }
            else {
                curCount--;
                $("#getCodeBtn").text("倒计时" + curCount + "秒");
            }
        }


        //验证手机号码
        function isPoneAvailable(poneInput) {
            var myreg = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
            if (!myreg.test(poneInput)) {
                return false;
            } else {
                return true;
            }
        }

    });
});