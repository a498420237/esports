define(function(require, exports, module) {
	var competitionUtil = {
			matchStatusClass : {//赛事状态转换:  1预热，2报名中，3开赛准备，4比赛中，5比赛结束,6已取消    目前只有这几种状态
				"1" : "reading",	
				"2" : "baominging",	
				"4" : "runing",	
				"5" : "end",	
			}
		};
	
	
	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,pagePlugin, util) {
		$("#live_content").paginator({
			itemTemplateId : 'itemTemplate',
			pageNavId : '',
			usepager:false,
			ajaxFuc : function(curentPage, renderHtml) {
				$.ajax({
					url : "/getLatestLiveStreamRoom",
					datatype : 'json',
					type : "post",
					data : {
						"offset" : 0,
						"limit" : 5
					},
					success : function(json) {
						var data = json.t;
						var paramObj = {
							total : data.total,
							page : data.page,
							list : data.result
						};
						renderHtml(paramObj);
					}
				});
			}
		});
		
		

		$("#forcast_content").paginator({
			itemTemplateId : 'forcastTemplate',
			pageNavId : '',
			usepager:false,
			
			ajaxFuc : function(curentPage, renderHtml) {
				$.ajax({
					url : "/getForecastList",
					datatype : 'json',
					type : "get",
					data : {
						"offset" : 0,
						"limit" : 3
					},
					success : function(json) {
						var data = json.t;
						var data = json.t;
						var results = data.result;
						for(var index in results){
							var r = results[index];							
							r.nowTime = new Date(r.nowTime).toLocaleDateString();
						}
						
						var paramObj = {
							total : data.total,
							page : curentPage,
							list : data.result
						};
						renderHtml(paramObj);
					}
				});
			}
		});
		
		
		$("#matchList").paginator({
			itemTemplateId : 'matchTemplate',
			pageNavId : '',
			usepager:false,
			ajaxFuc : function(curentPage, renderHtml) {
				$.ajax({
					url : "/getLatestMatch",
					datatype : 'json',
					type : "get",
					data : {
						"offset" : 0,
						"limit" : 6,
						"gameType" : 1
						
					},
					success : function(json) {
						var data = json.data;
						var data = json.t;
						var results = data.result;
						for(var index in results){
							var r = results[index];							
							r.matchStatusClass = competitionUtil.matchStatusClass[r.matchStatus];
							r.entryTime = new Date(r.startTime).toLocaleDateString();
							r.endEntryTime= new Date(r.startTime).toLocaleDateString();
						}
						
						var paramObj = {
							total : data.total,
							page : curentPage,
							list : data.result
						};
						renderHtml(paramObj);
					}
				});
			}
		});
		
		
		 //资讯内容列表
        $("#info_content").paginator({
            itemTemplateId: 'infoTemplate',
        	pageNavId : '',
			usepager:false,
            ajaxFuc: function (curentPage, renderHtml) {
                $.ajax({
                    url: "/getLatestContentInfo",
                    datatype: 'json',
                    type: "get",
                    data: {
                        "offset": 0,
                        "limit": 4
                    },
                    success: function (json) {
                    	var data = json.t;
						var paramObj = {
							total : data.total,
							page : data.page,
							list : data.result
						};
						renderHtml(paramObj);
                    }
                });
            }
        });
		
		
	});
});
