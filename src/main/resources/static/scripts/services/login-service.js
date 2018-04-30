define(function(require, exports, module) {
	seajs.use([ 'jquery','layerUtil'], function($,layerUtil) {
		// 绑定获取验证码事件
		var InterValObj; //timer变量，控制时间
		var count = 90; //间隔函数，1秒执行
		var curCount;//当前剩余秒数
		//timer处理函数
		function SetRemainTime() {
		            if (curCount == 0) {                
		                window.clearInterval(InterValObj);//停止计时器
		                $("#getCodeBtn").on("click",function(){
		                	var mobile = $("#phoneInput").val();
		    				if (mobile == "") {
		    					alert("请输入手机号");
		    					return;
		    				}
		    				if(isPoneAvailable(mobile)){
		    					alert("请输入正确的手机号");
		    				}
		    				   curCount = count;
		    				　　//设置button效果，开始计时
		    				     $(this).off("click");
		    				     $(this).removeAttr("class","bg-red");
		    				     $(this).attr("class","bg-999");
		    				     $(this).text("倒计时" + curCount + "秒");
		    				     InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
		    				$.ajax({
		    					url : "/sendMobileCode",
		    					datatype : 'json',
		    					type : "post",
		    					data : {
		    						"mobile" : mobile
		    					},success:function(obj){
		    						if(obj==null){
		    							alert("远程接口调用失败");
		    						}else{
		    							if(obj.code==200){
		    								alert("验证码发送成功");
		    							}else{
		    								alert("验证码发送失败请重试！");
		    							}
		    						}
		    					}
		    				});
		                	
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
		
		var bindGetCodeEvent = function() {
			$("#getCodeBtn").click(function() {
				var mobile = $("#phoneInput").val();
				if (mobile == "") {
					alert("请输入手机号");
					//layerUtil.alert("vvvv");
					//layerUtil.msg("aaa");
					return;
				}
				if(isPoneAvailable(mobile)){
					alert("请输入正确的手机号");
				}
				debugger;
				   curCount = count;
				　　//设置button效果，开始计时
				     $(this).off("click");
				     $(this).removeAttr("class","bg-red");
				     $(this).attr("class","bg-999");
				     $(this).text("倒计时" + curCount + "秒");
				     InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
				$.ajax({
					url : "/sendMobileCode",
					datatype : 'json',
					type : "post",
					data : {
						"mobile" : mobile
					},success:function(obj){
						if(obj==null){
							alert("远程接口调用失败");
						}else{
							if(obj.code==200){
								alert("验证码发送成功");
							}else{
								alert("验证码发送失败请重试！");
							}
						}
					}
				});
			});
		};
	
		// 绑定登录事件
		var isLogin=true;
		var bindLoginEvent = function() {
			$("#loginBtn").click(function() {
				var mobile = $("#phoneInput").val();
				if (mobile == "") {
					alert("请输入手机号");
					return;
				}
				if(isPoneAvailable(mobile)){
					alert("请输入正确的手机号");
				}
				var code = $("#codeInput").val();
				if (code == "") {
					alert("请输入验证码");
					return;
				}
				if(isLogin){
					isLogin=false;
				$.ajax({
					url : "/login",
					datatype : 'json',
					type : "post",
					async:false,
					data : {
						"mobile" : mobile,
						"code" : code
					},success:function(obj){
						if(typeof(obj) == "undefined"){
							alert("系统内部错误");
						}else{
							if(obj.code==200){

								  window.open('/user/index','_self');
							}else{
								if(typeof(obj.msg) == "undefined" ){
									alert("返回值错误");
								}else{
									alert(obj.msg);
								}
							}
						}
						isLogin=true;
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert("ajax调用错误");
                    }
				});
				}
				 return false; 
			});
		}
		function isPoneAvailable(poneInput) {  
	          var myreg=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;  
	          if (!myreg.test(poneInput)) {  
	              return false;  
	          } else {  
	              return true;  
	          }  
	      }  
		exports.bindEvents = function() {
			bindGetCodeEvent();
			bindLoginEvent();
		}
	});
});