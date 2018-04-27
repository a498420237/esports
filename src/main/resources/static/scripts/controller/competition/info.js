define(function(require, exports, module) {
	
	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,pagePlugin, util) {
		
		$("#againstInfoList").paginator({
			itemTemplateId : 'againstInfo_template',
			pageNavId : 'pageContainer',
			usepager:false,
			ajaxFuc : function(curentPage, renderHtml) {
//				var gameType = $("#competitionTab .nav.active").data("type");
				$.ajax({
					url : "/competition/againstInfo/"+11+"?num=-1",
					datatype : 'json',
					type : "get",
					data : {
					},
					success : function(json) {debugger;
						var data = json.t;
						var gameDayList = data.gameDayList;
						gameDayList = gameDayList.sort(function(a,b){//排序
					        return a.num - b.num;
					    });  
					    
						var paramObj = {
							 list : gameDayList
						};
						renderHtml(paramObj);
						
						$(".saishi_process .process_item").each(function(){
							var num=$(".saishi_process .process_item").length;
							$(this)[0].style.width=100/num+"%"	
						})
						$(".saishi_process .process_item").click(function(){
							$(this).addClass("active").siblings().removeClass("active");
						})
					}
				});
			}
		});
		
	});
	
	
});
