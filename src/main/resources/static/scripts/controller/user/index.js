define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		
		$(function(){
			init();
		});
		
		//获取列表数据
		function saveInfo(){
			var sex=0;
			sex= $("input[name='sex']:checked").val();
			var sex2=$("input[name='sex'][type='radio']:checked").val();
				var data={
						"userName":$("#userName").val(),
						"sex":sex,
						"age":$("#age").val(),
						"signature":$("#signature").val(),
						"area":$("#area").val(),
						"avatar":''
				};
				debugger;
					$.ajax({
						url : "/user/saveInfo",
						datatype : 'json',
						type : "GET",
						data : data,
						success : function(json) {
							debugger;
							if(json.code==200){
								
								layer.msg("修改成功",function(){
									window.location.reload();
								});
								
							}else{
								layer.msg(json.msg);
							}
						}
					});
		}
		
		
		
		
		function init(){
			//编辑
			$("#save").on("click",function(){
				saveInfo();
				
			})
			$("#editor").on("click",function(){
				$("#block1").fadeOut(300);
				setTimeout(function() {
					$("#block2").fadeIn();
				}, 300);
			})
			
			
			//显示绑定的游戏数据
			$.ajax({
						url : "/user/getGameInfo",
						datatype : 'json',
						type : "GET",
						//data : {gameId:1},
						success : function(json) {
							if(json.code==200){
								var gameHtml="";
								var NoExist=""
							
								var exitsHtml="<li>"
									+"<div class=\"ry\" id=\"gameId_$id$\" style=\"background: url($appLogo$) no-repeat;\">"
									+"<div class=\"info\" >"
									+"	<div class=\"tit\">$gameName$</div>"
									+"	"
									+"</div><div class=\"pro\" id=\"bind\">点击绑定账号</div>"
									+"</div></li>"
									$("#gameSelect").empty();
									$("#gameSelect").append("<option value='0'>请选择</option>");
									json.t.gameInfos.forEach(function(obj){
										gameHtml+= exitsHtml.replace(/\$\w+\$/gi, function(matchs) {
									        var returns = obj[matchs.replace(/\$/g, "")];		
									        return (returns + "") == "undefined"? "": returns;
									    });
										$("#gameSelect").append("<option value="+obj.id+">"+obj.gameName+"</option>")
									});
								$("#bind_access").html(gameHtml);
								getUserBindGame();
								$("#bind_game_sumbit").on("click",function(){
									var gameId=$("#gameSelect").val();
						        	var areaId=$("#GameAreaSelect").val();
						        	var gameRank=$("#GameRanksSelect").val();
						        	var role=$("#gameNickname").val();
						        	var printscreen="";
						        
									var data={
						        			"gameId":gameId,
						        			"areaId":areaId,
						        			"gameRank":gameRank,
						        			"role":role,
						        			"printscreen":printscreen
						        	};
						        	debugger;
						        	$.ajax({
										url : "/user/UserBindgameAccess",
										datatype : 'json',
										type : "get",
										data:data,
										success : function(json) {
											if(json.code==200){
												alert("成功");
											}else{
												alert(json.msg);
											}
										}
						        	});
								});
								
							}else{
								layer.msg(json.msg);
							}
						}
					});
			
			}
		
			function getUserBindGame(){
				//显示绑定的游戏数据
				$.ajax({
							url : "/user/getUserBindGame",
							datatype : 'json',
							type : "GET",
							success : function(json) {
								if(json.code==200){
									var gameHtml="";
									var Exist="<div class=\"set\"></div>"
									var newbind="";
										json.t.gameAccountInfos.forEach(function(obj){
										$("#gameId_"+obj.gameId).children().eq(1).remove();
										var gameName=$("#gameId_"+obj.gameId).children().eq(0).html();
											$("#gameId_"+obj.gameId).children().eq(0).html(gameName+"<p>"+obj.role+"</p><p>"+obj.areaName+"</p><p>"+obj.gameRank+"</p>"+Exist);
										});
	
									$("#bind").on("click",function(){
										var game_id
										$(".dialog").fadeIn();
									})
									$("#gameSelect").on("change",function(){
										debugger;
										var gameId=$(this).val();
										if(gameId==0){
											
										}else{
										GameArea(gameId);}
									})
									
								}else{
									layer.msg(json.msg);
								}
							}
						});
			}
			
			function GameArea(gameId){
				$.ajax({
					url : "/user/getGameArea",
					datatype : 'json',
					type : "GET",
					data:{"gameId":gameId},
					success : function(json) {
						if(json.code==200){
							$("#GameAreaSelect").empty();
							$("#GameAreaSelect").append("<option value='0'>请选择</option>");
							json.t.gameAreas.forEach(function(obj){
								$("#GameAreaSelect").append("<option value="+obj.id+">"+obj.areaName+"</option>")
							});
							
						}else{
							layer.msg(json.msg);
						}
					}
				});
				$.ajax({
					url : "/user/getGameRanks",
					datatype : 'json',
					type : "GET",
					data:{"gameId":gameId},
					success : function(json) {
						if(json.code==200){
							$("#GameRanksSelect").empty();
							//$("#GameAreaSelect").append("<option value='0'>请选择</option>");
							json.t.gameRanks.forEach(function(obj){
								$("#GameRanksSelect").append("<option value="+obj.rankName+">"+obj.rankName+"</option>")
							});
							
						}else{
							layer.msg(json.msg);
						}
					}
				});
			}
	});
});
