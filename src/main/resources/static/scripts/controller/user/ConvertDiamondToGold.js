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
							//layer.msg(json.msg);
							if(json.code==200){
								layer.msg("兑换成功",function(){window.location.reload();});
								//
							}else{
								layer.msg(json.msg);
							}
							isShow=true;
						}
						
					});
			
					
			}else{
				//alert("你点的太快了");
			}
		}
		
		$(function(){
			$('#btnSave').on("click",function() {
			    var diamond=$("#btn_value").val();
			    if(diamond==""){
			    	alert("请输入转换数量");
			    	return;
			    }
			     seniorLoad(diamond);
			     //window.location.reload();
			     return false;
			   });
		});	
			 
			
	});
});
