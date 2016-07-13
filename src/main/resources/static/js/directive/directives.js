define(["angular", "js/directive/rucsokDirective.js", "js/directive/errorDirective.js"], function(angular, rucsokDirective, errorDirective) {
	
	var directives = angular.module("directives", []);
	
	directives.directive("rucsok", rucsokDirective);
	directives.directive("error", errorDirective);
	
});