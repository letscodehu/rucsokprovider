define([ "js/controller/rucsokController.js","js/controller/loginController.js", "js/controller/errorController.js",
         "angular"
        ], function(rucsokController, loginController, errorController, angular) {
	
	var controllers = angular.module("controllers", []);
	
	controllers.controller("loginController", loginController);
	controllers.controller("rucsokController", rucsokController);
	controllers.controller("errorController", errorController);
	
})