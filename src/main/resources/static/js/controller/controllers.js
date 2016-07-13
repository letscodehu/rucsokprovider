define([ "js/controller/rucsokController.js","js/controller/errorController.js",
         "angular"
        ], function(rucsokController, errorController, angular) {
	
	var controllers = angular.module("controllers", []);
	
	controllers.controller("rucsokController", rucsokController);
	controllers.controller("errorController", errorController);
	
})