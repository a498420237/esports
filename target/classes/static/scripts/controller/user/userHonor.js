define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		$(function(){
			init();
			seniorLoad(0);
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
							"limit" : 15,"gameType":gametype
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
			   });
			}
	});
});
