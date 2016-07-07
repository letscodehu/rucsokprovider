define([
        "angular", 
        "js/controller/controllers.js",
        "js/directive/directives.js",
        "js/service/services.js"
        ], function(angular) {
	
	var app = new application();
	
	
	
	function application() {
		
	}
	
	application.prototype.init = function() {
		var app = angular.module("rucsok", [
		            "controllers",
		            "directives",
		            "services",
                ]);
		angular.bootstrap(document, ['rucsok']);
	}
	
	app.init();

});