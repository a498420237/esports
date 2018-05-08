define(function (require, exports, module) {
    seajs.use(['jquery', 'pagePlugin', 'utilService', 'template'], function ($, pagePlugin, util, template) {

        $(function () {
            init();
            seniorLoad();
            initResult();
        });
        var isShow = true;

        //获取列表数据
        function seniorLoad() {

            if (isShow) {
                isShow = false;
                $("#list_content").paginator({
                    itemTemplateId: 'itemTemplate',
                    pageNavId: '',
                    usepager: false,
                    useSeniorTemplate: true,
                    ajaxFuc: function (curentPage, renderHtml) {
                        var data = {
                            "activityType": 1
                        };

                        $.ajax({
                            url: "/user/pay/activitylist",
                            datatype: 'json',
                            type: "get",
                            data: data,
                            success: function (json) {
                                debugger;
                                var data = json.t;
                                renderHtml(data);
                                isShow = true;

                                $("#list_content li").click(function () {
                                    $("#list_content li").attr("class", "");
                                    $(this).attr("class", "active");

                                    var rmb = $("#list_content li.active").attr("payrmb");//支付人民币
                                    if (rmb) {
                                        $(".orang").html(rmb);
                                    }
                                })

                                $(".czfs ul li").click(function () {
                                    $(".czfs ul li").attr("class", "");
                                    $(this).attr("class", "active");
                                })


                            }
                        });
                    }
                });
            } else {
                //alert("你点的太快了");
            }
        }

        function init() {
            $(".btncre").on("click", function () {

                var id = $("#list_content li.active").attr("id"); //活动id
                var payid = $(".czfs ul li.active").attr("payid");//支付方式id标志

                if (id == undefined || id == null) {
                    layer.msg("请选择充值数量");
                }
                else if (payid == undefined || payid == null) {
                    layer.msg("请选择充值方式");
                }
                else {

                    var rmb = $("#list_content li.active").attr("payrmb");//支付人民币

                    var url;
                    if (payid == "2") {
                        url = "/user/pay/wxpay"
                    }
                    else if (payid == "1") {
                        url = "/user/pay/alipay"
                    }
                    //$("#popmoney").show();
                    $.ajax({
                        url: url,
                        datatype: 'json',
                        type: "post",
                        data: {
                            "body": "电竞e族",
                            "activityId": id,
                            "rmb": rmb

                        }, success: function (obj) {

                            if (obj.code == 200) {

                                var rs = $("body").append(obj.form);
                                $("form").attr("target", "_blank");
                                $("form").submit();
                            }
                        }
                    });
                }
                return false;
            });
        }

        function initResult() {
            var url = location.href;
            if (url.indexOf("total_amount") > 0) {
                $("#paysuceesspop").show();
            }
            else {
                $("#paysuceesspop").hide();
            }
        }
    });
});
