define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {

        var InterValObj; //timer变量，控制时间
        var count = 90; //间隔函数，1秒执行
        var curCount;//当前剩余秒数
		$(function(){
			init();
            bindGetCodeEvent();
            getEmailCode();
            emailComplete();
            changeEmailComplete();
		});
		
		//获取列表数据
		function saveInfo(){
			//var formData = new FormData($("#form_userinfo"));
			var formData = new FormData();
			sex= $("input[name='sex']:checked").val();
			 var formData = new FormData();
            formData.append("file", document.getElementById("myprefixfile").files[0]);
            formData.append("userName", $("#userName").val());
            formData.append("sex", sex);
            formData.append("age", $("#age").val());
            formData.append("signature", $("#signature").val());
            formData.append("area", $("#area").val());
            formData.append("avatar", "");
		    $.ajax({
		        //接口地址
		        url: '/user/saveInfo' ,
		        type: 'POST',
		        data: formData,
		        //async: false,
		        //cache: false,
		        contentType: false,
		        processData: false,
		        success: function (data) {
		            //成功的回调
		            if(data.code == 200){
		            	layer.msg("保存成功",function(){
							window.location.reload();
						});
		            }else{
		            	layer.msg("保存失败："+json.msg);
		            }
		        },
		        error: function (returndata) {
		           //请求异常的回调
		        	alert("网络访问失败，请稍后重试!");
		        }
		    });
			//var sex=0;
			//sex= $("input[name='sex']:checked").val();
				/*var data={
						"userName":$("#userName").val(),
						"sex":sex,
						"age":$("#age").val(),
						"signature":$("#signature").val(),
						"area":$("#area").val(),
						"avatar":myprefix
				};*/
			
					/*$.ajax({
						url : "/user/saveInfo",
						datatype : 'json',
						type : "GET",
						data : data,
						success : function(json) {
							
							if(json.code==200){
								
								layer.msg("修改成功",function(){
									window.location.reload();
								});
								
							}else{
								layer.msg(json.msg);
							}
						}
					});*/
		}
		
		
	
		function submitFile(){
			var formData = new FormData($("#form-add")[0]);
		    $.ajax({
		        //接口地址
		        url: '/user/PicturesSubmit' ,
		        type: 'POST',
		        data: formData,
		        async: false,
		        cache: false,
		        contentType: false,
		        processData: false,
		        success: function (data) {
		            //成功的回调
		            if(data.code == 200){
		            	layer.msg("保存成功:"+data.msg,{time:8*1000},function(){
							window.location.reload();
						});
		            }else{
		            	layer.msg("保存失败："+data.msg);
		            }
		        },
		        error: function (returndata) {
		           //请求异常的回调
		        	alert("网络访问失败，请稍后重试!");
		        }
		    });
		}
		function showImages(file,preview){
			var reader  = new FileReader();
			  reader.onloadend = function () {
				  preview.attr("src",reader.result);
			  }
			  if (file) {
				  reader.readAsDataURL(file);
			  } else {
				  preview.src="" ;
			  }
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
			$("#userPictures").on("change",function(){
				var files= document.querySelector('input[name="adduserPicturesfile"]').files;
				$(".newImages").remove();
				debugger;
				imageData = new FormData();
				for(var i=0;i<files.length;i++){
					
					var file=files[i];
					var imgId=file.name.substr(0,file.name.lastIndexOf(".")).toLowerCase()+"_"+i;
					var addtemhtml="<div class=\"list left newImages\">"
						+"<div  class=\"img\">"
						+"<img id='"+imgId+"' src='' style=\"width:126px; height:126px;\" alt='"+imgId+"' />"
						//+"<div class=\"ic tempic\"><img src=\"/images/person_close.png\"></div>"
						+"</div>"
						
						+"</div>";
					$(this).parent().parent().before(addtemhtml);
					
					var preview=$("#"+imgId);
					showImages(file,preview);
					
				}
				//$(this).parent().hide();
				  $("#pictures_list .tempic").on("click",function(){
					  var tempImg=$(this).parent().parent();
					  debugger;
					  if(window.confirm('你确定要删除吗？')){
						  tempImg.remove();
					  }
				  });
			});
			$("#submitFile").on("click",function(){
				var file=$("#userPictures").val();
				if(file=="" || file.name==""){
					layer.msg("请选择图片");
					return;
				}
				if(!isFileType(file)){
					layer.msg("图像的的类型必须(.jpg|.jpeg|.gif|.png)后缀");
					return;
				}
				submitFile();
			});
			$("#myprefix").on("click",function(){
				var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false; 
				if(ie){ 
				document.getElementById("myprefixfile").click(); 
				}else{
				var a=document.createEvent("MouseEvents");//FF的处理 
				a.initEvent("click", true, true);  
				document.getElementById("myprefixfile").dispatchEvent(a); 
				} 
			});
			
			$("#pictures_list .ic").on("click",function(){
				if(window.confirm('你确定要删除吗？')){
					 var Img=$(this).parent().parent();
					var pid=$(this).find("img").attr("alt");
					
					$.ajax({
						url : "/user/delUserPicture",
						datatype : 'json',
						type : "post",
						data:{"pId":pid},
						success : function(json) {
							if(json.code==200){
								layer.msg("删除成功",function(){
									Img.remove();
								});
							}else{
								layer.msg("删除失败："+json.msg);
							}
						}
					});
	              }else{
	                 //alert("取消");
	                 //return false;
	             }
			});
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
									+"</div><div class=\"pro bindGame\" >点击绑定账号</div>"
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
						        	if(gameId=="" || gameId==0){
						        		alert("请选择游戏类型");
						        		return;
						        	}
						        	if(areaId=="" || areaId==0){
						        		alert("请选择区服");
						        		return;
						        	}
						        	if(gameRank=="" || gameRank==0){
						        		alert("请选择游戏排名");
						        		return;
						        	}
						        	if(role==""){
						        		alert("请输入游戏昵称");
						        		return;
						        	}
									var data={
						        			"gameId":gameId,
						        			"areaId":areaId,
						        			"gameRank":gameRank,
						        			"role":role,
						        			"printscreen":printscreen
						        	};
						        	
						        	$.ajax({
										url : "/user/UserBindgameAccess",
										datatype : 'json',
										type : "get",
										data:data,
										success : function(json) {
											if(json.code==200){
												layer.msg("绑定成功",function(){
													window.location.reload();
												});
											}else{
												layer.msg(json.msg);
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
	
									$(".bindGame").on("click",function(){
										var game_id
										$(".dialog").fadeIn();
									})
									$("#gameSelect").on("change",function(){
										
										var gameId=$(this).val();
										if(gameId==0){
											
										}else{
											
											//	GameArea(gameId);
											
										}
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

        // 获取邮箱验证码
        function getEmailCode() {
            // 绑定新邮箱
            $("#bindEmailCodeBtn").click(function() {
                var email = $("#bindEmailInput").val();

                if (email == "") {
                    layer.msg("请输入邮箱");
                    return;
                }
                if(!isEmailAvaliable(email)){
                    layer.msg("请输入正确的邮箱");
                    return;
                }
                sendEmail(email, "bindEmail");
            });

            // 邮箱变更
            $("#oldEmailCodeBtn").click(function() {
                var email = $("#oldEmailInput").val();
                if (email == "") {
                    layer.msg("请输入邮箱");
                    return;
                }
                if(!isEmailAvaliable(email)){
                    layer.msg("请输入正确的邮箱");
                    return;
                }
                sendEmail(email, "resetBindEamil");
            });

            $("#newEmailCodeBtn").click(function() {
                var email = $("#newEmailInput").val();
                if (email == "") {
                    layer.msg("请输入邮箱");
                    return;
                }
                if(!isEmailAvaliable(email)){
                    layer.msg("请输入正确的邮箱");
                    return;
                }
                sendEmail(email, "bindEmail");
            });

        }

        //发送验证码
        function sendEmail(email, type){
            $.ajax({
                url : "/user/sendEmailCode",
                datatype : 'json',
                type : "post",
                data : {
                    "email" : email,
                    "type": type
                },success:function(obj){

                    if(obj==null){
                        layer.msg("远程接口调用失败");
                    }else{
                        if(obj.code==200){
                            layer.msg("验证码发送成功");
                        }else{
                            layer.msg("验证码发送失败请重试！");
                        }
                    }
                }
            });
        }

        function emailComplete() {
            // 绑定邮箱
            $("#bindEmailCompelete").click(function() {
                var email = $("#emailInput").val();
                if (email == "") {
                    layer.msg("请输入邮箱");
                    return;
                }
                if(!isEmailAvaliable(email)){
                    layer.msg("请输入正确的邮箱");
                    return;
                }

                var emailCode = $("#emailCode").val();

                if (emailCode == "") {
                    layer.msg("请输入验证码");
                    return;
                }

                $.ajax({
                    url : "/user/bindEmail",
                    datatype : 'json',
                    type : "post",
                    data : {
                        "email" : email,
                        "code": emailCode,
                        "oldAccount": oldEmail,
                        "verifyCode": oldEmailCode
                    },success:function(obj){
                        if(obj.code==200){
                            layer.msg("邮箱绑定成功");

                            // $(".dialog3").fadeIn();
                            $(".dialog4").fadeOut();
                            $(".dialog2").fadeOut();
                            $(".dialog1").fadeOut();


                        }else{
                            // layer.msg("邮箱绑定失败请重试！");
                            layer.msg(obj.msg);
                        }
                    }
                });
            });
        }

        function changeEmailComplete() {
            // 绑定邮箱
            $("#changeEmailCompelete").click(function() {
                var oldEmail = $("#oldEmailInput").val();
                var newEmail = $("#newEmailInput").val();
                if (oldEmail == "" || newEmail == "") {
                    layer.msg("请输入邮箱");
                    return;
                }
                if(!isEmailAvaliable(oldEmail) || !isEmailAvaliable(newEmail)){
                    layer.msg("请输入正确的邮箱");
                    return;
                }

                var oldEmailCode = $("#oldEmailCode").val();
                var newEmailCode = $("#newEmailCode").val();

                if (oldEmailCode == "" || newEmailCode=="") {
                    layer.msg("请输入验证码");
                    return;
                }

                $.ajax({
                    url : "/user/bindEmail",
                    datatype : 'json',
                    type : "post",
                    data : {
                        "email" : newEmail,
                        "code": newEmailCode,
                        "oldAccount": oldEmail,
                        "verifyCode": oldEmailCode
                    },success:function(obj){
                        $(".dialog4").fadeOut();
                        $(".dialog2").fadeOut();
                        $(".dialog1").fadeOut();

                        debugger;
                        if(obj.code==200){
                            layer.msg("邮箱绑定成功");
                        }else{
                            // layer.msg("邮箱绑定失败请重试！");
                            layer.msg(obj.msg);
                        }
                    }
                });
            });
        }


        var bindGetCodeEvent = function() {
            $("#getCodeBtn").click(function() {
                var mobile = $("#phoneInput").val();

                if (mobile == "") {
                    layer.msg("请输入手机号");
                    return;
                }
                if(!isPoneAvailable(mobile)){
                    layer.msg("请输入正确的手机号");
                    return;
                }
                curCount = count;
                $(this).off("click");
                $(this).removeAttr("class","bg-red");
                $(this).attr("class","bg-999");
                $(this).text("倒计时" + curCount + "秒");
                InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                sendsms(mobile);
            });
        };

        //发送验证码
        function sendsms(mobile){
            $.ajax({
                url : "/sendMobileCode",
                datatype : 'json',
                type : "post",
                data : {
                    "mobile" : mobile
                },success:function(obj){
                    debugger;
                    if(obj==null){
                        layer.msg("远程接口调用失败");
                    }else{
                        if(obj.code==200){
                            layer.msg("验证码发送成功");
                        }else{
                            layer.msg("验证码发送失败请重试！");
                        }
                    }
                }
            });
        }


        //timer处理函数
        function SetRemainTime() {
            if (curCount == 0) {
                window.clearInterval(InterValObj);//停止计时器
                $("#getCodeBtn").on("click",function(){
                    var mobile = $("#phoneInput").val();
                    if (mobile == "") {
                        layer.msg("请输入手机号");
                        return;
                    }
                    if(!isPoneAvailable(mobile)){
                        layer.msg("请输入正确的手机号");return;}
                    curCount = count;
                    //设置button效果，开始计时
                    $(this).off("click");
                    $(this).removeAttr("class","bg-red");
                    $(this).attr("class","bg-999");
                    $(this).text("倒计时" + curCount + "秒");
                    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                    sendsms(mobile);

                });
                $("#getCodeBtn").removeAttr("class","bg-999");
                $("#getCodeBtn").attr("class","bg-red");
                $("#getCodeBtn").text("发送验证码");

            }
            else {
                curCount--;
                $("#getCodeBtn").text("倒计时" + curCount + "秒");
            }
        }



        //验证手机号码
        function isPoneAvailable(poneInput) {
            var myreg=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
            if (!myreg.test(poneInput)) {
                return false;
            } else {
                return true;
            }
        }

        // 验证邮箱
        function isEmailAvaliable(email) {
            var myreg=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if (!myreg.test(email)) {
                return false;
            } else {
                return true;
            }
        }
        //判断文件类型
        function isFileType(filename){
        var type=(filename.substr(filename.lastIndexOf("."))).toLowerCase();
        if(type!=".jpg"&&type!=".gif"&&type!=".jpeg"&& type!=".png"){
        //alert("您上传图片的类型不符合(.jpg|.jpeg|.gif|.png)！");
        return false;
        }
        else{
        	return true;
        }
        }

    });

});
