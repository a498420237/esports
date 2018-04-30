define(function(require, exports, module) {

	var competitionUtil = {
	};

	function loadDatas(curentPage, renderHtml){
		$.ajax({
			url : "/user/myWarTeam/list",
			datatype : 'json',
			type : "get",
			data : {
				"offset" : curentPage,
				"limit" : 4
			},
			success : function(json) {
				var data = json.t;
				var results = data.result;
				var paramObj = {
					total : data.total,
					page : data.page,
					list : data.result,
					offset : data.offset
				};
				//competitionUtil.renderHtml(paramObj);
			}
		});
	}


	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,pagePlugin, util) {

		$("#competitionList").paginator({
			itemTemplateId : 'competitionList_template',
			ajaxFuc : function(curentPage, renderHtml) {
				//competitionUtil.renderHtml = renderHtml;
				loadDatas(curentPage);
			}
		});
		$(function(){
			loadDatas(0);
		});

		/*$("#competitionTab, #statusTab").click(function(){//tab点击
			loadDatas(0);
		});*/
	});


});
