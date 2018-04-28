define(function(require, exports, module) {
	seajs.use([ 'jquery','layer'], function($,layer) {

		// 绑定获取验证码事件
		var bindGetCodeEvent = function() {
			$("#getCodeBtn").click(function() {
				debugger;
				var mobile = $("#phoneInput").val();
				if (mobile == "") {
					layer.alert("请输入手机号");
					return;
				}
				$.ajax({
					url : "/sendMobileCode",
					datatype : 'json',
					type : "post",
					data : {
						"mobile" : mobile
					},success:function(){
						alert("发送成功,请注意接受短信。");
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
					data : {
						"mobile" : mobile,
						"code" : code
					},
					success : function(json) {
						debugger;
						//$(".poplogin").fadeIn();
						//$("#logi").text("测试用户");
						//$("#logi").attr("href","/user/index");
						window.location.href="http://localhost:8081/user/index";
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