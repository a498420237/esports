define(function(require, exports, module) {
	
	
	var dict = {
		serverType : {
			"9" : "QQ",
			"10" : "微信"
		},
		entryFeeType : {
			"4" : "砖石",
			"5" : "金币"
		}
	}
	
	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,pagePlugin, util) {
		
		/**
		 * 有一部分数据 详情接口没有 只能在列表拿到
		 */
		var dataCenter = {
			"matchId" : $("#hiddern_matchId").html(), 
			"totalPrize" : $("#hiddern_totalPrize").html(),
			"prizeType" : $("#hiddern_prizeType").html(), 
			"totalMumber" : $("#hiddern_totalMumber").html(), 
			"endTime" : $("#hiddern_endTime").html(),
		}
		
		var loadInfoData = function(num){//加载赛事主内容
			$.ajax({
				url : "/competition/info/"+dataCenter.matchId,
				datatype : 'json',
				type : "get",
				data : {
				},
				success : function(json) {
					if(!json){
						return;
					}
					
					var data = json;
					data.matchId = dataCenter.matchId;
					data.totalPrize = dataCenter.totalPrize;
					data.prizeType = dataCenter.prizeType;
					data.totalMumber = dataCenter.totalMumber;
					data.endTime = dataCenter.endTime;
					data.startTime = new Date(data.startTime).format("yyyy-MM-dd");
					data.entryFeeType = dict.entryFeeType[data.entryFeeType] ? dict.entryFeeType[data.entryFeeType] : "其他";
					data.serverType = dict.serverType[data.serverType] ? dict.serverType[data.serverType] : "其他赛区";
					
					//按步骤填充页面
			    	util.fillHtml("tabContent", $("#tabContent_template").html(), data); 
			    	util.fillHtml("prizeArea", $("#prizeArea_template").html(), data.matchPrize);
			    	util.fillHtml("payArea", $("#payArea_template").html(), data);
			    	
			    	//绑定事件
					$("#againstInfoList").on("click", ".process_item", function(){
						var num = $(this).data("num");
						loadAgainstInfo(num);
					});
					//关闭蒙版层
					$(".pay_do .cancel").click(function(){
						$(".mengbanDiv").removeClass("show")
					})
				}
			});
		}
			
		var loadAgainstInfo = function(num){//加载对阵信息
			$.ajax({
				url : "/competition/againstInfo/"+dataCenter.matchId+"?num="+num,
				datatype : 'json',
				type : "get",
				data : {
				},
				success : function(json) {
					var data = json.t;
					if(data == undefined || data.gameDayList == undefined){
						return;
					}
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
			    		if(againstPlan.winId != undefined){//已经有胜利方 则结束
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
			    	util.fillHtml("againstPlans", $("#againstPlan_template").val(), againstPlanList);
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
		var isSubmit=true;
		function pageInit(){
			$("#againstInfoList").on("click", ".process_item", function(){
				var num = $(this).data("num");
				loadAgainstInfo(num);
			});
			
			$("#enrollMatch").on("click", function(){//展示报名页面
					//$(".mengbanDiv").addClass("show");
				$.ajax({
					url : "/isLoginOrUser",
					datatype : 'json',
					type : "get",
					success : function(json) {
						var userInfo=json.t;
						debugger;
						if(json.code==200){
							var entryFeeType=$("#entryFeeType").text();//费用类型
							var entryFee=$("#entryFee").val();
							if(entryFeeType=="金币"){
								if(parseInt(userInfo.gold)>=parseInt(entryFee)){
									$("#gold_diamond").html("当前余额：("+userInfo.diamond+")金币");
								}
							}else{
								if(parseInt(userInfo.diamond)>=parseInt(entryFee)){
									$("#gold_diamond").html("当前余额：("+userInfo.diamond+")钻石");
									$("#gold_diamond").next().remove();
								}
							}
							/*$.ajax({
								url : "/user/getUserBindGame",
								datatype : 'json',
								type : "GET",
								success : function(json) {
									if(json.code==200){
											json.t.gameAccountInfos.forEach(function(obj){
											var gameName=$("#gameId_"+obj.gameId).children().eq(0).html();
											});

									}else{
										layer.msg(json.msg);
									}
								}
							});*/
							$(".mengbanDiv").addClass("show");
							
							$(".pay").on("click",function(){
								var matchId=dataCenter.matchId;
								if(isSubmit){
									isSubmit=false;
								
								$.ajax({
									url : "/competition/applyMatch",
									datatype : 'json',
									type : "GET",
									data:{"matchId":matchId},
									success : function(json) {
										isSubmit=true;
										if(json.code==200){
											alert("报名成功",function(){
												$(".mengbanDiv").removeClass("show");
											});
										}else{
											alert(json.msg);
											$(".mengbanDiv").removeClass("show");
										}
									}
								});
								}
							});
							}else{
								layer.msg("登录之后才能报名",function(){
									window.location.href="/login";
								});
							}
						}
				});
			});
			
			$("#pageNav a:first").click();
		}
		
		//***
		loadInfoData();
		loadAgainstInfo(0);
		pageInit();
	});
	
	
});
