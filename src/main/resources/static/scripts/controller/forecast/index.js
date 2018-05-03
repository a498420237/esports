define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		var isShow=true;
		//获取列表数据
		function seniorLoad(){
			
			debugger;
			if(isShow){
				isShow=false;
			$("#list_content").paginator({
				itemTemplateId : 'itemTemplate',
				pageNavId : 'pageContainer',
				usepager:true,
				useSeniorTemplate:true,
				ajaxFuc : function(curentPage, renderHtml) {
					var gameId=$("#gemelist_left_nav").children().find(".active").attr("name");
					var data={
							"offset" : curentPage,
							"limit" : 4
					};
					if(gameId!="" && gameId!=0){
						data.gameId=gameId;
					}
					$.ajax({
						url : "/forecast/list",
						datatype : 'json',
						type : "get",
						data : data,
						success : function(json) {
							var data = json.t;
							debugger;
							renderHtml(data);
							init();
							shezhigusuan();
							isShow=true;
						}
					});
				}
			});
			}else{
				//alert("你点的太快了");
			}
		}
		
		//获取游戏类型
		$("#gemelist_left_nav").paginator({
			itemTemplateId : 'leftNavTemplate',
			pageNavId : '',
			usepager:false,
			ajaxFuc : function(curentPage, renderHtml) {
				$.ajax({
					url : "/forecast/gemelist",
					datatype : 'json',
					type : "get",
					data : {"applySite" : 1,},
					success : function(json) {
						if(json.code==200){
							var data=json.t;
							var all={ id:0,name:"全部游戏",shortName:""};
							data.gameList.unshift(all);
							var paramObj = {
									list : data.gameList
								};
								renderHtml(paramObj);
								$("#gemelist_left_nav").children().eq(0).find("a").attr("class","active");
								
								seniorLoad();
								init();
					    	}else{
					    		alert(json.msg);
					    	}
					}
				});
			}
		});

		
		function init(){			//页面
			 $('.z_forecast_nav li a').on("click",function() {
			     $('.z_forecast_nav li a').removeClass("active");
			     $(this).addClass("active");
			     seniorLoad();
			   });
			   $('.z_list_con').on("click",function() {
			     var _this = $(this);
			     var notThis = $('.z_forecast_list .z_list_con').not(_this);
			     _this.parent().find(".z_list_ul").slideDown();
			     notThis.parent().find(".z_list_ul").slideUp();
			   });
			   $('.z_pager a').on("click",function() {
			     $('.z_pager a').removeClass("on");
			     $(this).addClass("on");

			   });
			   //赔率点击显示弹出框
		        $('.odds').click(function() {
		        	var l_h_id=$(this).attr("name");
		        	var l_h_ids=l_h_id.split("_");
		        	var lotteryId=l_h_ids[0];
		        	var handicapId=l_h_ids[1];
		        	$.ajax({
						url : "/isLoginOrUser",
						datatype : 'json',
						type : "get",
						success : function(json) {
							var userInfo=json.t;
							if(json.code==200){
								$.ajax({
									url : "/forecast/getLotteryInfo",
									datatype : 'json',
									type : "get",
									data : {"lotteryId" : lotteryId,},
									success : function(json) {
										var handicap=json.t;
										var handicapListInfos = [];
	
										json.t.lotteryInfo.handicapListInfos.forEach(function(obj){
											if(obj.handicapId==handicapId){
												handicapListInfos.push(obj);
											}
										});
										handicap.lotteryInfo.gold=userInfo.gold;
										handicap.lotteryInfo.diamond=userInfo.diamond;
										handicap.lotteryInfo.handicapId=handicapId;
										handicap.lotteryInfo.handicapListInfos=handicapListInfos;
										var data=[];
										data.push(handicap.lotteryInfo);
										var daa={ "total":10,"result":data};
										var contenthtml=template("z_modelsTemplate", daa);
										debugger;
										if(json.code==200){
											
											 layer.open({
										            type: 1,
										            title: false,
										            shadeClose: true,
										            shade: 0.5,
										            closeBtn: 1,
										            area: ['400px', '510px'],
										            //宽高
										            content: contenthtml
										          });
											 //弹出框选择操作效果
										        $(".clickNumber").on("click",function() {
										          $(this).parents(".lump").find(".click").removeClass("normal");
										          $(this).addClass("normal")
										          $("#quizMoney").val($(this).text());
										          shezhigusuan();
										        });
										        $(".clicktype").on("click",function() {
										          $(this).parents(".lump").find(".click").removeClass("normal");
										          $(this).addClass("normal");
										          shezhigusuan();
										        });
										        $("#quizMoney").blur(function(){
										        	 shezhigusuan();
										        	});
										        //关闭弹出层
										        $('.confirm').on("click",function() {
										        	
										        	var quizMoney=$("#quizMoney").val();
										        	var quizTeamType=$("#quizTeamType").find("a.normal").attr("name");
										        	var lotteryId=$("#lotteryId").val();
										        	var roundId=$("#roundId").val();
										        	var goldType=$("#goldType").val();
										        	debugger;
										        	if(quizMoney=="" ||quizMoney==null){
										        		layer.msg("请输入下注金额");
										        		return;
										        	}
										        	var data={
										        			"lottertType":1,
										        			"lotteryId":lotteryId,
										        			"quizMoney":quizMoney,
										        			"quizTeamType":quizTeamType,
										        			"roundId":roundId,
										        			"quizType":1,
										        			"clientType":0,
										        			"goldType":goldType
										        	};
										        	debugger;
										        	$.ajax({
														url : "/user/BetOrder/saveBet",
														datatype : 'json',
														type : "get",
														data:data,
														success : function(json) {
															if(json.code==200){
																layer.msg("成功");
															}else{
																alert(json.msg);
															}
														}
										        	});
										          layer.close(layer.index);
										        });
									    	}else{
									    		layer.msg("获取数据错误");
									    	}
									}
								});
								
								
						    	}else{
						    		window.location.href="/login";
						    	}
						}
					});
		         
		        });
		       
			}
		
		function shezhigusuan(){
			var a= $("#quizMoney").val();
	        var b=$("#redOdd").val();
	        var b2=$("#blueOdd").val();
	      	var quizTeamType=$("#quizTeamType").find("a.normal").attr("name");
	      	if(a!=""){
	      	var c=parseFloat(a)*parseFloat(b);
	      	if(quizTeamType==1){
	      		c=parseFloat(a)*parseFloat(b2);
	      	}
	          $("#estimate").text(c);
	      	}
		}
	});
});
