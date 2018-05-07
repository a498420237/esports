define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		
		$(function(){
			init();
			seniorLoad();
		});
		var isShow=true;
		//获取列表数据
		function seniorLoad(){

			if(isShow){
				isShow=false;
			$("#list_content").paginator({
				itemTemplateId : 'itemTemplate',
				pageNavId : '',
				usepager:false,
				useSeniorTemplate:true,
				ajaxFuc : function(curentPage, renderHtml) {
					var data={
							"activityType" : 1};
			
					$.ajax({
						url : "/user/pay/activitylist",
						datatype : 'json',
						type : "get",
						data : data,
						success : function(json) {
							debugger;
							var data = json.t;
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
		
		function init(){	
			$(".btncre").on("click",function(){
				
				 layer.open({
			            type: 1,
			            title: false,
			            shadeClose: true,
			            shade: 0.5,
			            closeBtn: 1,
			            area: ['400px', '510px'],
			            //宽高
			            content: $(".popmoney").html()
			          });
			})
		}
	});
});
