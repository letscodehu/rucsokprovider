define([
        "directives/addRucsokDirective", 
        "directives/rucsokPreviewDirective", 
  ], function(
		  addRucsokDirective, 
		  rucsokPreviewDirective) {
	
	var directives = angular.module("directives", []);
	
	directives.directive("addRucsok", addRucsokDirective);
	directives.directive("rucsokPreview", rucsokPreviewDirective);
	
});