define(function (require, exports, module) {
    seajs.use(['jquery', 'pagePlugin', 'utilService'], function ($, pagePlugin, util) {

        //资讯内容列表
        $("#list_content").paginator({
            itemTemplateId: 'listTemplate',
            pageNavId: 'pageContainer',
            usepager: true,
            useSeniorTemplate: true,
            ajaxFuc: function (curentPage, renderHtml) {
                $.ajax({
                    url: "/message/list",
                    datatype: 'json',
                    type: "get",
                    data: {
                        // "id": 78,
                        "offset": curentPage,
                        "limit": 4
                    },
                    success: function (json) {
                        var data = json.t;
                        renderHtml(data);
                        loadTitle();
                        init();
                    }
                });
            }
        });

        //资讯栏目列表
        function loadTitle() {
            $("#title_list").paginator({
                itemTemplateId: 'titleTemplate',
                pageNavId: '',
                usepager: false,
                useSeniorTemplate: true,
                ajaxFuc: function (curentPage, renderHtml) {
                    $.ajax({
                        url: "/message/messageTitle",
                        datatype: 'json',
                        type: "get",
                        data: {
                            // "id": 73,
                            "limit": 4
                        },
                        success: function (json) {
                            var data = json.t;
                            renderHtml(data);
                            // var width = Math.round(1 / data.total * 10000) / 100.00 + "%";
                            // $("#title_list .nav").css("width", width);

                            $(".saishi_nav .navz").each(function () {
                                var num = $(".saishi_nav .navz").length;
                                $(this)[0].style.width = 100 / num + "%"
                            })
                            $(".saishi_nav .navz").click(function () {

                                $(this).addClass("active").siblings().removeClass("active");
                                var channelId = $(this).attr("value");
                                //特定栏目下数据
                                $("#list_content").paginator({
                                    itemTemplateId: 'listTemplate',
                                    pageNavId: 'pageContainer',
                                    usepager: true,
                                    useSeniorTemplate: true,
                                    ajaxFuc: function (curentPage, renderHtml) {
                                        $.ajax({
                                            url: "/message/list",
                                            datatype: 'json',
                                            type: "get",
                                            data: {
                                                "offset": curentPage,
                                                "limit": 4,
                                                "belongsChannel": channelId
                                            },
                                            success: function (json) {
                                                var data = json.t;
                                                renderHtml(data);
                                                // loadTitle();
                                            }
                                        });
                                    }
                                });
                            })

                            $(".fenye a").click(function () {
                                $(this).addClass("active").siblings().removeClass("active");

                            })

                            // $('.nav').on("click", function () {
                            //     
                            //     $(".nav").attr("class", "nav active");
                            // });
                        }
                    });
                }
            });
        }


        function init() {
            //关键字搜索

            $('#searchMessage').on("click", function () {
                var key = $("#contentKey").val();
                $("#list_content").paginator({
                    itemTemplateId: 'listTemplate',
                    pageNavId: 'pageContainer',
                    usepager: true,
                    useSeniorTemplate: true,
                    ajaxFuc: function (curentPage, renderHtml) {
                        $.ajax({
                            url: "/message/searchByKey",
                            datatype: 'json',
                            type: "get",
                            data: {
                                "offset": curentPage,
                                "limit": 4,
                                "keyWord": key
                            },
                            success: function (json) {
                                var data = json.t;
                                renderHtml(data);
                                // loadTitle();
                            }
                        });
                    }
                });
            });


        }
    });
});
