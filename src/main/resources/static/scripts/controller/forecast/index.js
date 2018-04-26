define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,pagePlugin, util) {
		
		$("#list_content").paginator({
			itemTemplateId : 'itemTemplate',
			pageNavId : 'pageContainer',
			usepager:true,
			ajaxFuc : function(curentPage, renderHtml) {
				$.ajax({
					url : "/forecast/list",
					datatype : 'json',
					type : "get",
					data : {
						"offset" : curentPage,
						"limit" : 2
					},
					success : function(json) {
						var data = json.t;
						var paramObj = {
							total : data.total,
							page : data.page,
							list : data.result
						};
						renderHtml(paramObj);
					}
				});
			}
		});
		
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
					    	}else{
					    		alert(info.msg);
					    	}
					}
				});
			}
		});
	});
});
