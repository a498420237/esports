define(function (require, exports, module) {

    seajs.use(['jquery', 'pagePlugin', 'utilService', 'template'], function ($, pagePlugin, util, template) {

    	// Banner列表
		$("#banner_content").paginator({
			itemTemplateId : 'bannerTemplate',
			pageNavId : '',
			usepager : false,
			useSeniorTemplate : true,
			ajaxFuc : function(curentPage, renderHtml) {
				$.ajax({
					url : "/getbanner",
					datatype : 'json',
					type : "get",
					data : {
						"offset" : 0,
						"limit" : 5,
						"module":20
					},
					success : function(json) {
						debugger;
						var data = json.t;
						renderHtml(data);
					}
				});
			}
		});

        function getLiveList(){
        	 var gameId = "";
             var liveName = "";
            $("#live_list").paginator({
                itemTemplateId: 'liveList_template',
                pageNavId: 'pageContainer',
                usepager: true,
                useSeniorTemplate:true,
                ajaxFuc: function (curentPage, renderHtml) {
                    var data = {
                        "offset": curentPage,
                        "limit": 6
                    };
                    var page=curentPage;
                    gameId=$("#title_list span.active").find("img").attr("alt");
                    liveName=$("#contentKey").val();
                    if(gameId!=""){
                    	data.gameId = gameId;
                    }
                    if(liveName!=""){
                    data.liveName = liveName;
                    }
                    $.ajax({
                        url: "/live/list",
                        datatype: 'json',
                        type: "get",
                        data: data,
                        success: function (json) {
                        	
                            var data = json.t;
                            var paramObj = {
    								total : data.total,
    								offset : page,
    								result : data.result
    							};
                            renderHtml(paramObj);
                        }
                    });
                }
            });
        }




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
                        "applySite": 2
                    },
                    success: function (json) {
                        var data = json.t;
                        renderHtml(data);
                        init();
                        getLiveList();
                    }
                });
            }
        });


        function init(){
     
            $("#title_list span").click(function () {
                $("#title_list span").removeClass("active")
                $(this).addClass("active");
                getLiveList();
                event.stopPropagation();
            });
            
            $("#searchMessage").click(function(){
            	getLiveList();
                event.stopPropagation();
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
                    "limit": 6
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
