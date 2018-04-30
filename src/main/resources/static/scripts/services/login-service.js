define(function(require, exports, module) {
	seajs.use([ 'jquery','layerUtil'], function($,layerUtil) {
		// 绑定获取验证码事件
		var bindGetCodeEvent = function() {
			$("#getCodeBtn").click(function() {
				var mobile = $("#phoneInput").val();
				if (mobile == "") {
					alert("请输入手机号");
					return;
				}
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
		var bindLoginEvent = function() {
			$("#loginBtn").click(function() {
				var mobile = $("#phoneInput").val();
				if (mobile == "") {
					alert("请输入手机号");
					return;
				}
				var code = $("#codeInput").val();
				if (code == "") {
					alert("请输入验证码");
					return;
				}
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
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert("ajax调用错误");
                    }
				});
				 return false; 
				
			});
		}

		exports.bindEvents = function() {
			bindGetCodeEvent();
			bindLoginEvent();
		}
	});
});