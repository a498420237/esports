define(function(require, exports, module) {
	seajs.use([ 'jquery','loginService','layer'], function($,loginService,layer) {
		loginService.bindEvents();
	});
});
