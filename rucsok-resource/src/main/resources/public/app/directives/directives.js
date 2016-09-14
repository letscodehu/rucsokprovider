define([
        "directives/addRucsokDirective", 
        "directives/rucsokPreviewDirective", 
        "directives/imageRucsokDirective", 
        "directives/embedvideoRucsokDirective", 
        "directives/html5videoRucsokDirective", 
        "directives/changeLanguageDirective"
  ], function(
        addRucsokDirective, 
        rucsokPreviewDirective,
        imageRucsokDirective,
        embedvideoRucsokDirective,
        html5videoRucsokDirective,
        changeLanguageDirective
	) {
	
	var directives = angular.module("directives", []);
	
	directives.directive("addRucsok", addRucsokDirective);
	directives.directive("rucsokPreview", rucsokPreviewDirective);
	directives.directive("imageRucsok", imageRucsokDirective);
	directives.directive("embedvideoRucsok", embedvideoRucsokDirective);
	directives.directive("html5videoRucsok", html5videoRucsokDirective);
	directives.directive("changeLanguage", changeLanguageDirective);
});