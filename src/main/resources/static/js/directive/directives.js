define(["angular", "js/directive/rucsokDirective.js"], function(angular, rucsokDirective) {
	
	var directives = angular.module("directives", []);
	
	directives.directive("rucsok", rucsokDirective);
	
});