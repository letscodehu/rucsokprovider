define([ "controllers/rucsokController",
         "controllers/loginController", 
         "controllers/errorController", 
         "controllers/singleController",
         "controllers/addRucsokController",
         "ionic"
        ], function(rucsokController, loginController, errorController, singleController, addRucsokController) {
	
	var controllers = angular.module("controllers", []);	
	
	controllers.controller("addRucsokController", addRucsokController);
	controllers.controller("singleController", singleController);
	controllers.controller("loginController", loginController);
	controllers.controller("rucsokController", rucsokController);
	controllers.controller("errorController", errorController);
	
})