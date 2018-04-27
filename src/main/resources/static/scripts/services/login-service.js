define(function(require, exports, module) {
	seajs.use([ 'jquery' ], function($) {

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
					}
				});
			});
		};

		// 绑定登录事件
		var bindLoginEvent = function() {
			debugger;
			$("#loginBtn").click(function() {
				debugger;
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
						window.location.reload();
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