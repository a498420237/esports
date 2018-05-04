define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		
		$(function(){
			init();
			seniorLoad(0);
		});
		var isShow=true;
		//获取列表数据
		function seniorLoad(dateType){
//dateType 0：全部 1：本周 2：本月
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
							"limit" : 3,"dateType":dateType
					};
			
					$.ajax({
						url : "/user/BetOrder/list",
						datatype : 'json',
						type : "get",
						data : data,
						success : function(json) {
							
							if(json.code==200){
								var data = json.t;
								if(typeof(data) == "undefined" ){
									$("#list_content").html("暂无数据");
								}else{
									if(data.result.length==0){
										$("#list_content").html("暂无数据");
									}else{
								var paramObj = {
									total : data.total,
									page : data.offset,
									list : data.result
								};
								renderHtml(data);}
								}
								}else{
									alert("接口返回服务器内部错误");
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
			 $(".select_ul").click(function() {
				 
				var currency=  $('.tab span.sel').attr("name");
			     var dateId=$("select.select").val();
			     seniorLoad(currency,dateId);
			   });
			}
	});
});
