define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		
		$(function(){
			init();
			seniorLoad(0,0);
		});
		
		var isShow=true;
		//获取列表数据
		function seniorLoad(currency,dateId){

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
							"limit" : 15,currency:dateId,dateId:dateId
					};
					$.ajax({
						url : "/user/myMatch/list",
						datatype : 'json',
						type : "get",
						data : data,
						success : function(json) {
							var data = json.t;
							var paramObj = {
								total : data.total,
								page : data.offset,
								list : data.result
							};
							renderHtml(data);
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
			 $('.tab span').on("click",function() {
			     $('.tab span').removeClass("sel");
			     $(this).addClass("sel");
			     var currency=$(this).attr("name");
			     
			     var dateId=$("select.select").val();
			     
			     seniorLoad(currency,0);
			   });
			 $("#dataId").on("click",function() {
				 
				varcurrency=  $('.tab span.sel').attr("name");
			     var dateId=$(this).val();
			     
			     seniorLoad(currency,dateId);
			   });
			}
	});
});
