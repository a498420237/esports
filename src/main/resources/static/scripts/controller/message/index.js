define(function (require, exports, module) {
    seajs.use(['jquery', 'pagePlugin', 'utilService'], function ($, pagePlugin, util) {

        //资讯内容列表
        $("#list_content").paginator({
            itemTemplateId: 'listTemplate',
            pageNavId: 'pageContainer',
            usepager:true,
            ajaxFuc: function (curentPage, renderHtml) {
                $.ajax({
                    url: "/message/messageTitle",
                    datatype: 'json',
                    type: "get",
                    data: {
                        "offset": curentPage,
                        "limit": 2
                    },
                    success: function (json) {
                        var data = json.t;
                        var paramObj = {
                            total: data.total,
                            page: data.page,
                            list: data.result
                        };
                        renderHtml(paramObj);
                        // loadTitle();
                    }
                });
            }
        });

        //资讯栏目列表
        function loadTitle(){
            $("#title_list").paginator({
                itemTemplateId: 'titleTemplate',
                pageNavId: 'pageContainer',
                usepager:false,
                ajaxFuc: function (curentPage, renderHtml) {
                    $.ajax({
                        url: "/message/messageTitle",
                        datatype: 'json',
                        type: "get",
                        success: function (json) {
                            var data = json.t;
                            var paramObj = {
                                total: data.total,
                                list: data.result
                            };
                            renderHtml(paramObj);
                        }
                    });
                }
            });
        }
    });
});
