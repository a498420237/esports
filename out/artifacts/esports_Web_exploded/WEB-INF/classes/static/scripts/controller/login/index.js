define(function(require, exports, module) {
	seajs.use([ 'jquery','loginService','layerUtil'], function($,loginService,layerUtil) {
		loginService.bindEvents();
	});
});
