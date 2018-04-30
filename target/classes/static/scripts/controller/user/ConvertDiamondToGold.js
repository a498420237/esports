define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		
		var isShow=true;
		//获取列表数据
		function seniorLoad(diamond){
			if(isShow){
				isShow=false;
					debugger;
					$.ajax({
						url : "/user/ConvertDiamondToGold/save",
						datatype : 'json',
						type : "GET",
						data : {"diamond" : diamond},
						success : function(json) {
							debugger;
							if(json.code==200){
								alert("兑换成功");
							}else{
								alert(json.msg);
							}
							isShow=true;
						}
					});
				
		
			}else{
				//alert("你点的太快了");
			}
		}
		
		$(function(){
			debugger;
			$('#btnSave').on("click",function() {
			    var diamond=$("#btn_value").val();
			    if(diamond==""){
			    	alert("请输入转换数量");
			    	return;
			    }
			     seniorLoad(diamond);
			   });
		});	
			 
			
	});
});
