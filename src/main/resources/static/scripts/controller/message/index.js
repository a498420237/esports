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
                        "offset": curentPage,
                        "limit": 2
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
                        success: function (json) {
                            var data = json.t;
                            // renderHtml(data);
                            var width = Math.round(1 / data.total * 10000) / 100.00 + "%";
                            // debugger;
                            // $(".nav").css("width", width);
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
                                "limit": 2,
                                "keyWord": key
                            },
                            success: function (json) {
                                var data = json.t;
                                renderHtml(data);
                                loadTitle();
                            }
                        });
                    }
                });
            });
        }
    });
});
