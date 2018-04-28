define(function(require, exports, module) {
	
	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,pagePlugin, util) {
		
		var loadData = function(num){
			$.ajax({
				url : "/competition/againstInfo/"+11+"?num="+num,
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
			    		util.fillHtml("againstInfoList", $("#againstInfo_template").val(), gameDayList);
			    	}
			    	util.fillHtml("againstPlans", $("#againstPlan_template").val(), againstPlanList);
			    	pageInit();
				}
			});
		}
		
		function pageInit(){
			$(".saishi_process .process_item").each(function(){
				var num=$(".saishi_process .process_item").length;
				$(this)[0].style.width=100/num+"%"	
			})
			$(".saishi_process .process_item").click(function(){
				$(this).addClass("active").siblings().removeClass("active");
			})
		}
		
		loadData(0);
		$("#againstInfoList").on("click", ".process_item", function(){
			var num = $(this).data("num");
			loadData(num);
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
	});
	
	
});
