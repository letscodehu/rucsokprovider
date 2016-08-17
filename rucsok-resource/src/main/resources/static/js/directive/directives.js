define(["angular", "js/directive/rucsokDirective.js", "js/directive/loginDirective.js","js/directive/errorDirective.js"], function(angular, rucsokDirective,loginDirective, errorDirective) {
	
	var directives = angular.module("directives", []);
	
	directives.directive("login", loginDirective);
	directives.directive("rucsok", rucsokDirective);
	directives.directive("error", errorDirective);
	
});