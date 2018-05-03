define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		$(function(){
			init();
			seniorLoad(1);
			getUserGameAccess(1);
		});
		var isShow=true;
		//获取列表数据
		function seniorLoad(gametype){
			if(isShow){
				isShow=false;
			$("#list_content").paginator({
				
				itemTemplateId : 'itemTemplate',
				pageNavId : 'pageContainer',
				usepager:true,
				useSeniorTemplate:true,
				ajaxFuc : function(curentPage, renderHtml) {
					var data={
							"offset" : curentPage,
							"limit" : 5,"gameType":gametype
					};
					debugger;
					$.ajax({
						url : "/user/UserHonor/list",
						datatype : 'json',
						type : "GET",
						data : data,
						success : function(json) {
							if(json.code==200){
							var data = json.t;
							if(typeof(data) == "undefined"){
								$("#list_content").html("暂无数据");
							}else{
							var paramObj = {
								total : data.total,
								page : data.offset,
								list : data.result
							};
							renderHtml(data);
							}
							}else{
								alert(json.msg);
							}
							isShow=true;
						}
					});
				}
			});
			}else{
				//alert("你点的太快了");
			}
		}
		
		function init(){			//页面
			 $('#game_type span').on("click",function() {
			     $('.tab span').removeClass("select");
			     $(this).addClass("select");
			     var gametype=$(this).attr("name");
			     seniorLoad(gametype);
			     getUserGameAccess(gametype);
			   });
			
			}
		
		function getUserGameAccess(gametype){
			 $.ajax({
					url : "/user/getUserBindGame",
					datatype : 'json',
					type : "GET",
					success : function(json) {
						if(json.code==200){
							debugger;
							if(json.t.gameAccountInfos.length>0){
								var isBind=false;
								json.t.gameAccountInfos.forEach(function(obj){
									if(obj.gameId==gametype){
										isBind=true;
										$("#areaName").text("区服："+obj.areaName);
										$("#gameRank").text("段位："+obj.gameRank);
									}
								});
								if(!isBind){
									$("#areaName").text("区服：未绑定");
									$("#gameRank").text("段位：未绑定");
								}
								}else{
									$("#areaName").text("区服：未绑定");
									$("#gameRank").text("段位：未绑定");
								}
						}else{
							layer.msg(json.msg);
						}
					}
				});
		}
	});
});
