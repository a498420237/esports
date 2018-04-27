define(function(require, exports, module) {
	
	//赛事状态转换:  1预热，2报名中，3开赛准备，4比赛中，5比赛结束,6已取消	
	var matchStatusClass = {//目前只有这几种状态
		"1" : "reading",	
		"2" : "baominging",	
		"4" : "runing",	
		"5" : "end",	
	};
	
	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,pagePlugin, util) {
		
		$("#competitionList").paginator({
			itemTemplateId : 'competitionList_template',
			pageNavId : 'pageContainer',
			ajaxFuc : function(curentPage, renderHtml) {
				
				var gameType = $("#competitionTab .nav.active").data("type");
				$.ajax({
					url : "/competition/list",
					datatype : 'json',
					type : "get",
					data : {
						"offset" : curentPage,
						"limit" : 4,
						"gameType" : gameType
						
					},
					success : function(json) {
						var data = json.t;
						var results = data.result;
						for(var index in results){
							var r = results[index];							
							r.matchStatusClass = matchStatusClass[r.matchStatus];
						}
						
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
