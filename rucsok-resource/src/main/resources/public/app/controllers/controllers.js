define([ "controllers/loginController", 
         "controllers/errorController", 
         "controllers/singleController",
         "controllers/addRucsokController",
         "controllers/rucsokPreviewController",
         "controllers/dashboardController",
         "controllers/menuController",
         "ionic"
        ], function(
        		loginController, 
        		errorController, 
        		singleController,
        		addRucsokController, 
        		rucsokPreviewController,
        		dashboardController,
        		menuController
        		) {
	
	var controllers = angular.module("controllers", []);	
	
	controllers.controller("addRucsokController", addRucsokController);
	controllers.controller("rucsokPreviewController", rucsokPreviewController);
	controllers.controller("singleController", singleController);
	controllers.controller("loginController", loginController);
	controllers.controller("dashboardController", dashboardController);
	controllers.controller("errorController", errorController);
	controllers.controller("menuController", menuController);

})