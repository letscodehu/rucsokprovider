define([ "controllers/rucsokController",
         "controllers/loginController", 
         "controllers/errorController", 
         "controllers/singleController",
         "controllers/addRucsokController",
         "controllers/rucsokPreviewController",
         "ionic"
        ], function(
        		rucsokController, 
        		loginController, 
        		errorController, 
        		singleController,
        		addRucsokController, 
        		rucsokPreviewController) {
	
	var controllers = angular.module("controllers", []);	
	
	controllers.controller("addRucsokController", addRucsokController);
	controllers.controller("rucsokPreviewController", rucsokPreviewController);
	controllers.controller("singleController", singleController);
	controllers.controller("loginController", loginController);
	controllers.controller("rucsokController", rucsokController);
	controllers.controller("errorController", errorController);
	
})