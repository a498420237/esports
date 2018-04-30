define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		$(function(){
			seniorLoad("teamInfoArea");
			$(".table_head  .left label").click(function(){
				var currency=$(this).attr("name");
				var className=$(this).attr("class");
				if("active"!=className ){
					if("wz" ==currency){
						seniorLoad("teamInfoArea");
					}else if("jd"==currency){
						seniorLoad("itemTemplate");
					}
					var ind=$(this).index();
					$(this).addClass("active").siblings().removeClass("active");
					$(".historyTable .table_box ").eq(ind).addClass("show").siblings().removeClass("show");
				}
			})
		});
		var isShow=true;
		var isShow2=true;
		//获取列表数据
		function seniorLoad(tempId){
			if(isShow){
				isShow=false;
				$("#teamInfo").paginator({
					itemTemplateId : tempId,
					usepager:false,
					useSeniorTemplate:true,
					ajaxFuc : function(curentPage, renderHtml) {
						var data={
							"offset" : curentPage,
							"limit" : 2
						};

						$.ajax({
							url : "/user/myWarTeam/list",
							datatype : 'json',
							type : "get",
							data : data,
							success : function(json) {
								var data = json.t;
								var paramObj = {
									total : data.total,
									page : data.offset,
									list : data.troops
								};
								$(".mdat").html(data.newMsgCount);
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
