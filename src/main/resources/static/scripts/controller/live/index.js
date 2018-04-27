define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		
		//获取列表数据
		function load(gameId){
			$("#list_content").paginator({
				itemTemplateId : 'itemTemplate',
				pageNavId : 'pageContainer',
				usepager:true,
				ajaxFuc : function(curentPage, renderHtml) {
					var data={
							"offset" : curentPage,
							"limit" : 3
					};
					if(gameId!=""){
						data.gameId=gameId;
					}
					$.ajax({
						url : "/forecast/list",
						datatype : 'json',
						type : "get",
						data : data,
						success : function(json) {
							var data = json.t;
							var paramObj = {
								total : data.total,
								page : data.offset,
								list : data.result,
								children:data.result.handicapListInfos
							};
							renderHtml(paramObj);
							init();
						}
					});
				}
			});
		}
		//获取游戏类型
		$("#gemelist_left_nav").paginator({
			itemTemplateId : 'leftNavTemplate',
			pageNavId : '',
			usepager:false,
			ajaxFuc : function(curentPage, renderHtml) {
				$.ajax({
					url : "/forecast/gemelist",
					datatype : 'json',
					type : "get",
					data : {"applySite" : 1,},
					success : function(json) {
						if(json.code==200){
							var data=json.t;
							var all={ id:0,name:"全部游戏",shortName:""};
							data.gameList.unshift(all);
							var paramObj = {
									list : data.gameList
								};
								renderHtml(paramObj);
								$("#gemelist_left_nav").children().eq(0).find("a").attr("class","active");
								
								load("");
								init();
					    	}else{
					    		alert(json.msg);
					    	}
					}
				});
			}
		});

		

	});
});
