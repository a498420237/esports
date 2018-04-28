define(function(require, exports, module) {
	
	var competitionUtil = {
		matchStatusClass : {//赛事状态转换:  1预热，2报名中，3开赛准备，4比赛中，5比赛结束,6已取消    目前只有这几种状态
			"1" : "reading",	
			"2" : "baominging",	
			"4" : "runing",	
			"5" : "end",	
		}
	};
	
	function loadDatas(curentPage, renderHtml){
		var gameType = $("#competitionTab .nav.active").data("type");
		var statuType = $("#statusTab .active").data("type");
		$.ajax({
			url : "/competition/list",
			datatype : 'json',
			type : "get",
			data : {
				"offset" : curentPage,
				"limit" : 4,
				"gameType" : gameType,
				"statuType" : statuType
			},
			success : function(json) {
				var data = json.t;
				var results = data.result;
				for(var index in results){
					var r = results[index];							
					r.matchStatusClass = competitionUtil.matchStatusClass[r.matchStatus];
					r.startTime = r.startTime == undefined ? "" :new Date(r.startTime).format("yyyy-MM-dd");
					r.endTime = r.endTime == undefined ? "" : new Date(r.endTime).format("yyyy-MM-dd");
				}
				
				var paramObj = {
					total : data.total,
					page : curentPage,
					list : data.result
				};
				competitionUtil.renderHtml(paramObj);
			}
		});
	}
	
	
	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,pagePlugin, util) {
		
		$("#competitionList").paginator({
			itemTemplateId : 'competitionList_template',
			pageNavId : 'pageContainer',
			ajaxFuc : function(curentPage, renderHtml) {
				competitionUtil.renderHtml = renderHtml;
				loadDatas(curentPage);
			}
		});
		
		$("#competitionTab, #statusTab").click(function(){//tab点击
			loadDatas(1);
		});
	});
	
	
});
