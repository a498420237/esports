define(function(require, exports, module) {
	
	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,pagePlugin, util) {
		
		var matchId = $("#hiddern_matchId").html(); //比赛id字段
		var totalPrize = $("#hiddern_totalPrize").html(); //比赛id字段
		var prizeType = $("#hiddern_prizeType").html(); //比赛id字段
		var totalMumber = $("#hiddern_totalMumber").html(); //比赛id字段
		
		var loadInfoData = function(num){//加载赛事主内容
			$.ajax({
				url : "/competition/info/"+matchId,
				datatype : 'json',
				type : "get",
				data : {
				},
				success : function(json) {
					if(!json){
						return;
					}
					
					var data = json;
					data.matchId = matchId;
					data.totalPrize = totalPrize;
					data.prizeType = prizeType;
					data.totalMumber = totalMumber;
					data.startTime = new Date(data.startTime).format("yyyy-MM-dd");
					
					//按步骤填充页面
			    	util.fillHtml("tabContent", $("#tabContent_template").html(), data); 
			    	util.fillHtml("prizeArea", $("#prizeArea_template").html(), data.matchPrize);
			    	//绑定事件
					$("#againstInfoList").on("click", ".process_item", function(){
						var num = $(this).data("num");
						loadAgainstInfo(num);
					});
				}
			});
		}
			
		var loadAgainstInfo = function(num){//加载对阵信息
			$.ajax({
				url : "/competition/againstInfo/"+matchId+"?num="+num,
				datatype : 'json',
				type : "get",
				data : {
				},
				success : function(json) {
					var data = json.t;
					var gameDayList = data.gameDayList;
					gameDayList = gameDayList.sort(function(a,b){//排序
				        return a.num - b.num;
				    });  
					
			    	var currGameDay = "";
			    	var currNum = num == 0 ? gameDayList.length - 1 : 0;
		    		var currGameDay = gameDayList[currNum];
		    		currGameDay.dataClass = "active";
		    		
			    	var againstPlanList = currGameDay.againstPlanList;	
			    	var gameDay = new Date(currGameDay.gameDay).format("yyyy-MM-dd");
			    	for(var index in againstPlanList){
			    		var againstPlan = againstPlanList[index];
			    		againstPlan.gameDay = gameDay;
			    		if(againstPlan.winId != undefined){
			    			againstPlan.statuClass =  "end";
			    			againstPlan.statuName = "已结束";
			    		}else{
			    		   againstPlan.statuClass =  "runing";
			    		   againstPlan.statuName = "正在进行";
			    		}
			    	}
					
			    	if(num == 0){//初始化时
			    		util.fillHtml("againstInfoList", $("#againstInfo_template").html(), gameDayList);
			    	}
			    	util.fillHtml("againstPlans", $("#againstPlan_template").html(), againstPlanList);
					$(".saishi_process .process_item").each(function(){
						var num=$(".saishi_process .process_item").length;
						$(this)[0].style.width=100/num+"%"	
					})
					$(".saishi_process .process_item").click(function(){
						$(this).addClass("active").siblings().removeClass("active");
					})
				}
			});
		}
		
		function pageInit(){
			$("#againstInfoList").on("click", ".process_item", function(){
				var num = $(this).data("num");
				loadAgainstInfo(num);
			});
			
			$("#pageNav").on("click", "a", function(){
				var tab = $(this).data("tab");
				if(tab == 6){//支付tab
					$(".mengbanDiv").addClass("show");
					return;
				}
				
				$(".tabContent").hide();
				$(".tabContent.tab"+tab).show();
			});
			
			$("#pageNav a:first").click();
		}
		
		//***
		loadInfoData();
		loadAgainstInfo(0);
		pageInit();
	});
	
	
});
