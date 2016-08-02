define([ "controllers/rucsokController","controllers/loginController", "controllers/errorController", "controllers/singleController",
         "ionic"
        ], function(rucsokController, loginController, errorController, singleController) {
	
	var controllers = angular.module("controllers", []);
	
	
	controllers.controller("singleController", singleController);
	controllers.controller("loginController", loginController);
	controllers.controller("rucsokController", rucsokController);
	controllers.controller("errorController", errorController);
	
})