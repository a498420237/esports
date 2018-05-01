define(function (require, exports, module) {

    seajs.use(['jquery', 'pagePlugin', 'utilService', 'template'], function ($, pagePlugin, util, template) {

        var gameId = "";
        var liveName = "";

        function getLiveList(){
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
                    data.gameId = gameId;
                    data.liveName = liveName;
                    $.ajax({
                        url: "/live/list",
                        datatype: 'json',
                        type: "get",
                        data: data,
                        success: function (json) {
                            var data = json.t;
                            renderHtml(data);
                            init();
                        }
                    });
                }
            });
        }

        // getLiveList();


        // 资讯栏目列表
        $("#title_list").paginator({
            itemTemplateId: 'titleTemplate',
            pageNavId: '',
            usepager: false,
            useSeniorTemplate: true,
            ajaxFuc: function (curentPage, renderHtml) {
                $.ajax({
                    url: "/live/liveTitle",
                    datatype: 'json',
                    type: "get",
                    data: {
                        "limit": 4
                    },
                    success: function (json) {
                        var data = json.t;
                        renderHtml(data);
                        $(".z_panel_title .nav").each(function () {
                            var num = $(".z_panel_title .nav").length + 1;
                            $(this)[0].style.width = 100 / num + "%"
                        })
                        $(".z_panel_title .nav").click(function () {
                            $(this).addClass("active").siblings().removeClass("active");
                            gameId = $(this).attr("value");
                            liveName = "";
                            //特定栏目下数据
                            getLiveList();
                        })

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
                $(this).addClass("active");
            });

            $('#searchLiveName').on("click", function () {
                liveName = $("#liveName").val();
                getLiveList();
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
