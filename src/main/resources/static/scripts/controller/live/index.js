define(function (require, exports, module) {

    seajs.use(['jquery', 'pagePlugin', 'utilService', 'template'], function ($, pagePlugin, util, template) {

        var gameId = "";
        var liveName = "";

        $("#live_list").paginator({
            itemTemplateId: 'liveList_template',
            pageNavId: '',
            usepager: true,
            useSeniorTemplate:true,
            ajaxFuc: function (curentPage, renderHtml) {
                var data = {
                    "offset": curentPage,
                    "limit": 6
                };
                if (gameId != "") {
                    data.gameId = gameId;
                }
                if (liveName != "") {
                    data.liveName = "xx";
                }
                $.ajax({
                    url: "/live/list",
                    datatype: 'json',
                    type: "get",
                    data: data,
                    success: function (json) {
                    	debugger
                        var data = json.t;
                        renderHtml(data);
                        init();
                    }
                });
            }
        });


        function init(){
            $(".z_Video_con").mouseover(function (event) {
                var _this = $(this)
                var notThis = $('li .z_Video_con').not(_this);
                notThis.parents("li").find(".z_shade").hide();
                _this.parents("li").find('.z_shade').show();
            })
            $(".z_panel .z_panel_title span").click(function () {
                $(".z_panel .z_panel_title span").removeClass("active")
                $(this).addClass("active")
            });

        }


         // 推荐直播
        $("#recommendLive_list").paginator({
            itemTemplateId: 'recommendLive_template',
            pageNavId: '',
            usepager: false,
            // useSeniorTemplate:true,
            ajaxFuc: function (curentPage, renderHtml) {
                var data = {
                    "offset": curentPage,
                    "limit": 3
                };
                if (gameId != "") {
                    data.gameId = gameId;
                }
                if (liveName != "") {
                    data.liveName = "xx";
                }
                $.ajax({
                    url: "/live/recommendLive",
                    datatype: 'json',
                    type: "get",
                    data: data,
                    success: function (json) {
                        var data = json.t;
                        var paramObj = {
                            total : data.total,
                            page : curentPage,
                            list : data.liveInfo
                        };
                        renderHtml(paramObj);
                        init();
                    }
                });
            }
        });



    });
});
