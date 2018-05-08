define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		
		$(function(){
			init();
			seniorLoad();
		});
		
		var isShow=true;
		//获取正在进行中
		function seniorLoad(){
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
							"limit" : 5
					};
					$.ajax({
						url : "/user/myMatch/list",
						datatype : 'json',
						type : "get",
						data : data,
						success : function(json) {
							var data = json.t;
							if (typeof(data) == "undefined"){ 
								//alert("undefined"); 
									$("#list_content").html("暂无进行中赛事数据");
								}else{
									if(data.result.length==0){
										$("#list_content").html("暂无历史赛季数据");
									}else{
										renderHtml(data);
									}
								}
							isShow=true;
						}
					});
				}
			});
			}else{
				layer.msg("页面正在加载中。。。");
			}
		}
		//获取历史赛事
		function oldLoad(currency,dateId){
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
							"limit" : 5
					};
					$.ajax({
						url : "/user/myMatch/oldlist",
						datatype : 'json',
						type : "get",
						data : data,
						success : function(json) {
							var data = json.t;
							debugger;
							if (typeof(data) == "undefined"){ 
									

									$("#list_content").html("暂无历史赛季数据");
								}else{
									if(data.result.length==0){
										$("#list_content").html("暂无历史赛季数据");
									}else{
										renderHtml(data);
									}
									
								}
							isShow=true;
						}
					});
				}
			});
			}else{
				layer.msg("页面正在加载中。。。");
			}
		}
		function init(){		
			$(".table_head  .left label").on("click",function(){
				var ind=$(this).index();
				$(this).addClass("active").siblings().removeClass("active");
				$(".historyTable .table_box ").eq(ind).addClass("show").siblings().removeClass("show");
				if(ind==0){
					seniorLoad();
				}else{
					oldLoad();
				}
				
			});
			$(".table_head .right label").on("click",function(){
				$(this).addClass("active").siblings().removeClass("active");
				debugger;
				
			});
			
			
		}
			
			
	});
});
