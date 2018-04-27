define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
		var isShow=true;
		//获取列表数据
		function seniorLoad(gameId){
			debugger;
			if(isShow){
				isShow=false;
			$("#list_content").paginator({
				itemTemplateId : 'itemTemplate',
				pageNavId : 'pageContainer',
				usepager:true,
				useSeniorTemplate:true,
				ajaxFuc : function(curentPage, renderHtml) {
					var data={
							"offset" : curentPage,
							"limit" : 2
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
								list : data.result
							};
							renderHtml(data);
							init();
							isShow=true;
						}
					});
				}
			});
			}else{
				//alert("你点的太快了");
			}
		}
		
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
								list : data.result
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
								
								seniorLoad("");
								init();
					    	}else{
					    		alert(json.msg);
					    	}
					}
				});
			}
		});

		
		function init(){			//页面
			 $('.z_forecast_nav li a').on("click",function() {
			     $('.z_forecast_nav li a').removeClass("active");
			     $(this).addClass("active");
			     var id=$(this).attr("name");
			     seniorLoad(id);
			   });
			   $('.z_list_con').on("click",function() {
			     var _this = $(this);
			     var notThis = $('.z_forecast_list .z_list_con').not(_this);
			     _this.parent().find(".z_list_ul").slideDown();
			     notThis.parent().find(".z_list_ul").slideUp();
			   });
			   $('.z_pager a').on("click",function() {
			     $('.z_pager a').removeClass("on");
			     $(this).addClass("on");

			   });
			   //赔率点击显示弹出框
			   $('.odds').on("click",function() {
			     layer.open({
			       type: 1,
			       title: false,
			       shadeClose: true,
			       shade: 0.5,
			       closeBtn: 0,
			       area: ['390px', '500px'],
			       //宽高
			       content: $('.z_models')
			     })
			   });
			   //弹出框选择操作效果
			   $(".click").on("click",function() {
			     $(this).parents(".lump").find(".click").removeClass("normal");
			     $(this).addClass("normal")
			   });
			   //关闭弹出层
			   $('.confirm').on("click",function() {
			     layer.close(layer.index);
			   });
			}
	});
});
