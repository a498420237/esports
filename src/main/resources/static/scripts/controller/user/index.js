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
	});
});
