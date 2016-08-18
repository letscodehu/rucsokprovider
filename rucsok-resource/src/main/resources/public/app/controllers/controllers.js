define([ "controllers/loginController", 
         "controllers/errorController", 
         "controllers/singleController",
         "controllers/addRucsokController",
         "controllers/rucsokPreviewController",
         "controllers/freshController",
         "controllers/hotController",
         "controllers/jokeController",
         "controllers/profileController",
         "controllers/menuController",
         "ionic"
        ], function(
        		loginController, 
        		errorController, 
        		singleController,
        		addRucsokController, 
        		rucsokPreviewController,
        		freshController,
        		hotController,
        		jokeController,
        		profileController,
        		menuController
        		) {
	
	var controllers = angular.module("controllers", []);	
	
	controllers.controller("addRucsokController", addRucsokController);
	controllers.controller("rucsokPreviewController", rucsokPreviewController);
	controllers.controller("singleController", singleController);
	controllers.controller("loginController", loginController);
	controllers.controller("freshController", freshController);
	controllers.controller("hotController", hotController);
	controllers.controller("jokeController", jokeController);
	controllers.controller("errorController", errorController);
	controllers.controller("profileController", profileController);
	controllers.controller("menuController", menuController);

})