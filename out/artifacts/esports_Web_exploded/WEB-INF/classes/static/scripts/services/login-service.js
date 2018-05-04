define(function(require, exports, module) {
	seajs.use([ 'jquery','layerUtil'], function($,layerUtil) {
		// 绑定获取验证码事件
		var bindGetCodeEvent = function() {
			$("#getCodeBtn").click(function() {
				//
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
								alert(obj.msg);
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
						
						if(obj==null){
							alert("远程接口调用失败");
						}else{
							if(obj.code==200){
								alert("成功");
								//window.location.href="http://localhost:8081/user/index";
							}else{
								alert(obj.msg);
							}
						}
					}, error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
                    },
                    complete: function(XMLHttpRequest, textStatus) {
                        this; // 调用本次AJAX请求时传递的options参数
                    }
				});
				
			});
		}

		exports.bindEvents = function() {
			bindGetCodeEvent();
			bindLoginEvent();
		}
	});
});