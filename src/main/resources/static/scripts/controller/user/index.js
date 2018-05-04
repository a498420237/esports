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
			/*//弹框
			$("#bind").on("click",function(){
				$(".dialog").fadeIn();
			})
			$(".cancel").on("click",function(){
				$(".dialog").fadeOut();
			})
			$(document).mouseup(function(e){
		        var _con = $('.dialog'); 
		        if(_con.is(e.target) && _con.has(e.target).length === 0){ 
		           	$(_con).fadeOut();
		        }
		    });
		    //手机
		    $("#phone").on("click",function(){
				$(".dialog1").fadeIn();
			})
			$(".phone-can").on("click",function(){
				$(".dialog1").fadeOut();
			})
			$(document).mouseup(function(e){
		        var _con = $('.dialog1'); 
		        if(_con.is(e.target) && _con.has(e.target).length === 0){ 
		           	$(_con).fadeOut();
		        }
		    });
		    //邮箱
		    $("#email").on("click",function(){
				$(".dialog2").fadeIn();
			})
			$(".phone-can").on("click",function(){
				$(".dialog2").fadeOut();
			})
			$(document).mouseup(function(e){
		        var _con = $('.dialog2'); 
		        if(_con.is(e.target) && _con.has(e.target).length === 0){ 
		           	$(_con).fadeOut();
		        }
		    });
		    //成功
		    $(".complete").on("click",function(){
				$(".dialog3").fadeIn();
				$(".dialog2").fadeOut();
				$(".dialog1").fadeOut();
			})
			$("#close").on("click",function(){
				$(".dialog3").fadeOut();
			})
			$(document).mouseup(function(e){
		        var _con = $('.dialog3'); 
		        if(_con.is(e.target) && _con.has(e.target).length === 0){ 
		           	$(_con).fadeOut();
		        }
		    });*/
			}

        // 获取邮箱验证码
        function getEmailCode() {
            $("#emailCodeBtn").click(function() {
                var email = $("#emailInput").val();

                if (email == "") {
                    layer.msg("请输入邮箱");
                    return;
                }
                if(!isEmailAvaliable(email)){
                    layer.msg("请输入正确的邮箱");
                    return;
                }
                curCount = count;
                $(this).off("click");
                $(this).removeAttr("class","bg-red");
                $(this).attr("class","bg-999");
                $(this).text("倒计时" + curCount + "秒");
                InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
                sendEmail(email);
            });
        }

        //发送验证码
        function sendEmail(email){
            $.ajax({
                url : "user/sendEmailCode",
                datatype : 'json',
                type : "post",
                data : {
                    "email" : email,
                    "type": "bindEmail"
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
            $("#emailCompelete").click(function() {
                var email = $("#emailInput").val();
                if (email == "") {
                    layer.msg("请输入邮箱");
                    return;
                }
                if(!isEmailAvaliable(mobile)){
                    layer.msg("请输入正确的邮箱");
                    return;
                }

                var emailCode = $("#emailCode").val();

                $.ajax({
                    url : "user/bindEmail",
                    datatype : 'json',
                    type : "post",
                    data : {
                        "email" : email,
                        "code": emailCode,
                        "type": "1",
                        "oldAccount": "",
                        "verifyCode": ""
                    },success:function(obj){
                        if(obj.code==200){
                            layer.msg("邮箱绑定成功");
                        }else{
                            layer.msg("邮箱绑定失败请重试！");
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

    });

});
