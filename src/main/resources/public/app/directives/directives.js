define(["directives/addRucsokDirective", 
  ], function(addRucsokDirective) {
	
	var directives = angular.module("directives", []);
	
	directives.directive("addRucsok", addRucsokDirective);
	
});