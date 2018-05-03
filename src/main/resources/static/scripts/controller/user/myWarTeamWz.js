define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		var troopsId;
		var captainId;
		var memberNew;
		$(function(){
			seniorLoad("teamInfoArea");
			$("#backTeam").click(function() {
				backTeam();
			});
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
			$(".playapply").click(function() {
				poprdsq();
			});
			//弹框
			$(".table_head  .left label").click(function(){
				var currency=$(this).attr("name");
				var className=$(this).attr("class");
				if("active"!=className ){
					if("wz" ==currency){
						$("#create").hide();
						$("#wz").show();

						$("#teamInfo").show();
						$(".historyTable").hide();
						$(".playapply").show();
						seniorLoad("teamInfoArea");
					}else if("jd"==currency){
						$("#wz").hide();

						$("#create").show();
						$("#teamInfo").hide();
						$(".historyTable").show();
						$(".playapply").hide();
						seniorLoad2("itemTemplate");
					}
					var ind=$(this).index();
					$(this).addClass("active").siblings().removeClass("active");
					//$(".historyTable .table_box ").eq(ind).addClass("show").siblings().removeClass("show");
				}
			})
		});
		var isShow=true;
		var isShow2=true;
		var isShow3=true;
		var isShow4=true;
		function backTeam() {
			var data={
				"troopsId":troopsId,
			};
			//退出战队
			$.ajax({
				url : "/user/myWarTeam/backTeam",
				datatype : 'json',
				type : "get",
				data : data,
				success : function(json) {
					var data = json.code;
					$(".exitzd").hide();
					if("0"==data){
						location.reload();
					}else{
						layer.alert(json.msg);
					}

				}
			});
		}
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
							"limit" : 5
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
								var p = {
									troopsId : data.troops[0].id
								};
								$.ajax({
									url : "/user/myWarTeam/newMemberlist",
									datatype : 'json',
									type : "get",
									data : p,
									success : function(json) {
										var d = json.t;
										memberNew=d.result.length;
										$(".news").html(memberNew);
									}
								});
								troopsId=data.troops[0].id;
								$("#tid").val(troopsId);
								captainId =data.troops[0].captainId;
								seniorLoad3("tabNo1","teamHonor","pageContainer1",-1,"/user/myWarTeam/teamHonorOrHistoryList");
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
		function seniorLoad2(tempId){
			if(isShow2){
				isShow2=false;
				$("#teamList").paginator({
					itemTemplateId : tempId,
					pageNavId : 'pageContainer',
					usepager:true,
					useSeniorTemplate:true,
					ajaxFuc : function(curentPage, renderHtml) {
						var data={
							"offset" : curentPage,
							"limit" : 5,
							"gameType":2
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
								isShow2=true;
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
							"troopsId":troopsId,
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
		function poprdsq() {
			 isShow4=true;
			if(isShow4){
				isShow4=false;
				$("#newMemberlist").paginator({
					itemTemplateId : "teamnNewMember",
					usepager:false,
					useSeniorTemplate:true,
					ajaxFuc : function(curentPage, renderHtml) {
						var p2 = {
							troopsId : troopsId
						};
						$.ajax({
							url : "/user/myWarTeam/newMemberlist",
							datatype : 'json',
							type : "get",
							data : p2,
							success : function(json) {
								var data = json.t;
								renderHtml(data);
								isShow4=true;
								$(".poprdsq").fadeIn();
							}
						});
					}
				});
			}
		}
	});
});
