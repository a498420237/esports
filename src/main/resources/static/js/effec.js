
$(function() {
	


	$(".main_visual").hover(function(){
		$("#btn_prev,#btn_next").fadeIn()
		},function(){
		$("#btn_prev,#btn_next").fadeOut()
		})
	$dragBln = false;
	$(".main_image").touchSlider({
		flexible : true,
		speed : 200,
		btn_prev : $("#btn_prev"),
		btn_next : $("#btn_next"),
		paging : $(".flicking_con a"),
		counter : function (e) {
			$(".flicking_con a").removeClass("on").eq(e.current-1).addClass("on");
		}
	});
	$(".main_image").bind("mousedown", function() {
		$dragBln = false;
	})
	$(".main_image").bind("dragstart", function() {
		$dragBln = true;
	})
	$(".main_image a").click(function() {
		if($dragBln) {
			return false;
		}
	})
	timer = setInterval(function() { $("#btn_next").click();}, 5000);
	$(".main_visual").hover(function() {
		clearInterval(timer);
	}, function() {
		timer = setInterval(function() { $("#btn_next").click();}, 5000);
	})
	$(".main_image").bind("touchstart", function() {
		clearInterval(timer);
	}).bind("touchend", function() {
		timer = setInterval(function() { $("#btn_next").click();}, 5000);
	})






		$(".teamd").hover(function(){
		$("#btn_prev1,#btn_next1").fadeIn()
		},function(){
		$("#btn_prev1,#btn_next1").fadeOut()
		})
	$dragBln = false;
	$(".main_image1").touchSlider({
		flexible : true,
		speed : 200,
		btn_prev : $("#btn_prev1"),
		btn_next : $("#btn_next1"),
		paging : $(".flicking_con a"),
		counter : function (e) {
			$(".flicking_con a").removeClass("on").eq(e.current-1).addClass("on");
		}
	});
	$(".main_image1").bind("mousedown", function() {
		$dragBln = false;
	})
	$(".main_image1").bind("dragstart", function() {
		$dragBln = true;
	})
	$(".main_image1 a").click(function() {
		if($dragBln) {
			return false;
		}
	})
	timer = setInterval(function() { $("#btn_next1").click();}, 5000);
	$(".main_visual1").hover(function() {
		clearInterval(timer);
	}, function() {
		timer = setInterval(function() { $("#btn_next1").click();}, 5000);
	})
	$(".main_image1").bind("touchstart", function() {
		clearInterval(timer);
	}).bind("touchend", function() {
		timer = setInterval(function() { $("#btn_next1").click();}, 5000);
	})






//我的战队-转移队长的弹窗
$(".zydz").click(function(){
			$(".exitzydz").fadeIn();			
		});	
	$(".pubclose").click(function(){
			$(".exitzydz").hide();			

		});


//我的战队-队长踢出战队成员的弹窗
$(".tcdz").click(function(){
			$(".exittcdz").fadeIn();			
		});	
	$(".pubclose").click(function(){
			$(".exittcdz").hide();			

		});






//我的战队-身份验证
$("").click(function(){
			$(".popsfyz").fadeIn();			
		});	
	$(".pubclose").click(function(){
			$(".popsfyz").hide();			

		});




//我的战队-入队申请
$(".team .playapply").click(function(){
			$(".poprdsq").fadeIn();
		});
	$(".pubclose").click(function(){
			$(".poprdsq").fadeOut();

		});


//我的战队-身份验证提示






//我的战队-队员退出战队弹窗
$("#exitzdd").click(function(){
			$(".exitzd").fadeIn();			

		});	
	$(".pubclose").click(function(){
	
			$(".exitzd").hide();			

		});


	$(".close").click(function(){
	
			$(".popmoney").hide();			

		});

	//队长转移

	$("#dzchang").click(function(){
	
			$(".popwrit").fadeIn();			

		});	
	$(".pubclose").click(function(){
	
			$(".popwrit").fadeOut();			

		});





	$("#clcma").click(function(){
	
			$(".popmatch").slideToggle();			

		});	


});


