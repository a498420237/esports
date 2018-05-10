define(function(require, exports, module) {
	var competitionUtil = {
		matchStatusClass : {// 赛事状态转换: 1预热，2报名中，3开赛准备，4比赛中，5比赛结束,6已取消
			// 目前只有这几种状态
			"1" : "reading",
			"2" : "baominging",
			"4" : "runing",
			"5" : "end",
		}
	};
	var gameList = [];

	function getGameName(id) {
		for ( var index in gameList) {
			var r = gameList[index];
			if (r.id == id) {
				return r.name;
			}
		}
	}

	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,
			pagePlugin, util) {
		
		$(function(){
			//登录弹框
			$("#logi").click(function(){
				$(".poplogind").fadeIn(2000);			

			});	
		$(".pubclose").click(function(){
		
				$(".poplogind").fadeOut(2000);			

			});
		})
		
		// Banner列表
		$("#banner_content").paginator({
			itemTemplateId : 'bannerTemplate',
			pageNavId : '',
			usepager : false,
			useSeniorTemplate : true,
			ajaxFuc : function(curentPage, renderHtml) {
				$.ajax({
					url : "/getbanner",
					datatype : 'json',
					type : "get",
					data : {
						"offset" : 0,
						"limit" : 5,
						"module":15
					},
					success : function(json) {
						var data = json.t;
						renderHtml(data);
					}
				});
			}
		});
		// 资讯内容列表
		$("#info_content").paginator({
			itemTemplateId : 'infoTemplate',
			pageNavId : '',
			usepager : false,
			useSeniorTemplate : true,
			ajaxFuc : function(curentPage, renderHtml) {
				$.ajax({
					url : "/getLatestContentInfo",
					datatype : 'json',
					type : "get",
					data : {
						"offset" : 0,
						"limit" : 5
					},
					success : function(json) {
						var data = json.t;
						var results =data.result;
						var paramObj = {
							list : data.result
						};
						renderHtml(paramObj);
					}
				});
			}
		});
		/**
		 * 获取游戏类别
		 */
		$("#gemelist").paginator(
				{
					itemTemplateId : 'gameListTemplate',
					pageNavId : '',
					usepager : false,
					ajaxFuc : function(curentPage, renderHtml) {
						$.ajax({
							url : "/forecast/gemelist",
							datatype : 'json',
							type : "get",
							data : {
								"applySite" : 1,
							},
							success : function(json) {
								if (json.code == 200) {
									var data = json.t;
									var paramObj = {
										list : data.gameList
									};
									gameList = data.gameList;
									renderHtml(paramObj);
									$("#gemelist").children().eq(0).attr("class", "current");
								    var gametype=$("#gemelist").children().eq(0).children().attr("name");
								    var statType= $(".popmatch").find("li.current").attr("name");
									seniorLoad(gametype,statType);
									loadmatch(gametype,statType);
									$("#gemelist li").on("click",function(){
										
										$("#gemelist li").removeAttr("class","current");
										$(this).attr("class","current")
										//loadMatchList()
										loadmatch($(this).find("a").attr("name"),$(".popmatch").find("li.current").attr("name"));
									});
									$(".popmatch li").on("click",function(){
										
										var gemeType=$("#gemelist").find("li.current").children().attr("name");
										loadmatch(gemeType,$(this).attr("name"))
									});
								} else {
									alert(json.msg);
								}
							}
						});
					}
				});
		function loadmatch(gameType,statuType){
			if (typeof(statuType) == "undefined" || statuType=="" ||statuType=="0"){ 
				statuType=-1;
			}
			$("#matchList").paginator({
				itemTemplateId : 'matchTemplate',
				pageNavId : '',
				usepager : false,
				useSeniorTemplate : true,
				ajaxFuc : function(curentPage, renderHtml) {
					if(statuType==null||statuType==""){
						statuType="";
					}
					$.ajax({
						url : "/competition/list",
						datatype : 'json',
						type : "get",
						data : {
							"offset" : 0,
							"limit" : 6,
							"gameType" : gameType,
							"statuType" : statuType
						},
						success : function(json) {
							if(json.code==200){
							var data = json.t;
							var results = data.result;
							var paramObj = {
								total : data.total,
								page : 0,
								list : data.result
							};
							renderHtml(paramObj);
							}
						}
					});
				}
			});
		}
		
		// 获取列表数据
		function seniorLoad(gameType,status) {
			
				$("#live_content").paginator({
					itemTemplateId : 'liveitemTemplate',
					pageNavId : '',
					usepager : false,
					useSeniorTemplate : true,
					ajaxFuc : function(curentPage, renderHtml) {
						$.ajax({
							url : "/getLatestLiveStreamRoom",
							datatype : 'json',
							type : "post",
							data : {
								"offset" : 0,
								"limit" : 5
							},
							success : function(json) {
								
								var data = json.t;
								var results = data.result;
								for ( var index in results) {
									var r = results[index];
									r.gameName = getGameName(r.gameId);
								}
								var paramObj = {
									total : data.total,
									page : data.page,
									list : results
								};
								renderHtml(paramObj);
							}
						});
					}
				});

				$("#forcast_content").paginator(
						{
							itemTemplateId : 'forcastTemplate',
							pageNavId : '',
							usepager : false,
							useSeniorTemplate : true,
							ajaxFuc : function(curentPage, renderHtml) {
								$.ajax({
									url : "/forecast/list",
									datatype : 'json',
									type : "get",
									data : {
										"offset" : 0,
										"limit" : 3
									},
									success : function(json) {
										var data = json.t;
										var results = data.result;
										for ( var index in results) {
											var r = results[index];
											r.nowTime = new Date(r.nowTime)
													.toLocaleDateString();
										}
										var paramObj = {
											total : data.total,
											page : curentPage,
										list : data.result
									};
									renderHtml(paramObj);
								}
							});
						}
					});
				
				
				// 战队内容列表
				$("#troopList1").paginator({
					itemTemplateId : 'troopTemplate',
					pageNavId : '',
					usepager : false,
					useSeniorTemplate : true,
					ajaxFuc : function(curentPage, renderHtml) {
						$.ajax({
							url : "/getLatestTroops",
							datatype : 'json',
							type : "get",
							data : {
								"offset" : 0,
								"limit" : 3
							},
							success : function(json) {
								var data = json.t;
								var results =data.result;
								for ( var index in results) {
									var r = results[index];
									r.gameName = getGameName(r.gameType);
								}
								var paramObj = {
									list : data.result
								};
								renderHtml(paramObj);
							}
						});
					}
				});
				// 战队内容列表
				$("#troopList2").paginator({
					itemTemplateId : 'troopTemplate',
					pageNavId : '',
					usepager : false,
					useSeniorTemplate : true,
					ajaxFuc : function(curentPage, renderHtml) {
						$.ajax({
							url : "/getLatestTroops",
							datatype : 'json',
							type : "get",
							data : {
								"offset" : 4,
								"limit" : 3
							},
							success : function(json) {
								var data = json.t;
								var results =data.result;
								for ( var index in results) {
									var r = results[index];
									r.gameName = getGameName(r.gameType);
								}
								var paramObj = {
									list : data.result
								};
								renderHtml(paramObj);
							}
						});
					}
				});
				
				
		}
	});
});
