define(function(require, exports, module) {
	seajs.use([ 'jquery','loginService'], function($,loginService) {
		loginService.bindEvents();
	});
});
