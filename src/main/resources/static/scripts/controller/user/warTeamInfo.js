define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		var troopsId;
		var captainId;
		$(function(){
			seniorLoad();
			seniorLoad3("tabNo1","teamHonor","pageContainer1",-1,"/user/myWarTeam/teamHonorOrHistoryList");
			$("ul.tabs li").click(function() {
				$("ul.tabs li").removeClass("active");
				$(this).addClass("active");
				$(".tab_content").hide();
				var activeTab = $(this).find("a").attr("href");
				$(activeTab).fadeIn();
				if("#tab1" == activeTab){
					seniorLoad3("tabNo1","teamHonor","pageContainer1",-1,"/user/myWarTeam/teamHonorOrHistoryList");
				}else if("#tab2" == activeTab){
					seniorLoad3("tabNo2","teamMember","pageContainer2",-1,"/user/myWarTeam/teamMemberList");
				}else if("#tab3" == activeTab){
					seniorLoad3("tabNo3","teamHistory","pageContainer3",-1,"/user/myWarTeam/teamHonorOrHistoryList");
				}
				return false;
			});
			$(".table_head  .left label").click(function(){
				var currency=$(this).attr("name");
				var className=$(this).attr("class");
				if("active"!=className ){
					if("wz" ==currency){
						$("#create").hide();
						$("#wz").show();

						$("#teamInfo").show();
						$(".historyTable").hide();
						seniorLoad("teamInfoArea");
					}else if("jd"==currency){
						$("#wz").hide();

						$("#create").show();
						$("#teamInfo").hide();
						$(".historyTable").show();
						seniorLoad2("itemTemplate");
					}
					var ind=$(this).index();
					$(this).addClass("active").siblings().removeClass("active");
					//$(".historyTable .table_box ").eq(ind).addClass("show").siblings().removeClass("show");
				}
			})
		});
		var isShow=true;
		var isShow3=true;
		//获取列表数据
		function seniorLoad(){
			if(isShow){
				isShow=false;
				$("#teamInfo").paginator({
					itemTemplateId : "itemTemplate",
					usepager:false,
					useSeniorTemplate:true,
					ajaxFuc : function(curentPage, renderHtml) {
						var data={
							"troopsId":id,
						};
						$.ajax({
							url : "/user/myWarTeam/teamInfo",
							datatype : 'json',
							type : "get",
							data : data,
							success : function(json) {
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
		function seniorLoad3(tabNo1,tempId,page,type,url){
			if(isShow3){
				isShow3=false;
				$("#"+tabNo1).paginator({
					itemTemplateId : tempId,
					pageNavId : page,
					usepager:true,
					useSeniorTemplate:true,
					ajaxFuc : function(curentPage, renderHtml) {
						var data={
							"offset" : curentPage,
							"limit" : 5,
							"troopsId":id,
							"operation":type
						};

						$.ajax({
							url : url,
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
								if("tabNo2"==tabNo1){
									data.page["captainId"] = captainId;
									renderHtml(data.page);
								}else{
									renderHtml(data);
								}
								isShow3=true;
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
