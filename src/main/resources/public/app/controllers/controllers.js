define([ "controllers/rucsokController","controllers/loginController", "controllers/errorController",
         "ionic"
        ], function(rucsokController, loginController, errorController) {
	
	var controllers = angular.module("controllers", []);
	
	controllers.controller("loginController", loginController);
	controllers.controller("rucsokController", rucsokController);
	controllers.controller("errorController", errorController);
	
})