define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		$(function(){
			seniorLoad(1);
			$(".table_head  .left label").click(function(){
				var currency=$(this).attr("name");
				var className=$(this).attr("class");
				if("active"!=className ){
					if("wz" ==currency){
						seniorLoad(1);
					}else if("jd"==currency){
						seniorLoad(2);
					}
					var ind=$(this).index();
					$(this).addClass("active").siblings().removeClass("active");
					$(".historyTable .table_box ").eq(ind).addClass("show").siblings().removeClass("show");
				}
			})
		});
		var isShow=true;
		//获取列表数据
		function seniorLoad(dateId){
			if(isShow){
				isShow=false;
				$("#teamList").paginator({
					itemTemplateId : 'itemTemplate',
					pageNavId : 'pageContainer',
					usepager:true,
					useSeniorTemplate:true,
					ajaxFuc : function(curentPage, renderHtml) {
						var data={
							"offset" : curentPage,
							"limit" : 5,"gameType":dateId
						};

						$.ajax({
							url : "/user/myWarTeam/nothingList",
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
	});
});
