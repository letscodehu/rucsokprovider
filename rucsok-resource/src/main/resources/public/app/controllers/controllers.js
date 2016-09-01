define([ "controllers/loginController", 
         "controllers/errorController", 
         "controllers/singleController",
         "controllers/addRucsokController",
         "controllers/rucsokPreviewController",
         // Dashboard tabs
         "controllers/dashboard/freshController",
         "controllers/dashboard/hotController",
         "controllers/dashboard/jokeController",
         
         "controllers/menuController",
         
         // profile tabs
         "controllers/profileController",
         "controllers/profile/profileCommentController",
         "controllers/profile/profilePostController",
         "controllers/profile/profileUpvoteController",
         
         
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
        		menuController,
        		profileController,
        		profileCommentController,
        		profilePostController,
        		profileUpvoteController
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
	
	controllers.controller("profileCommentController", profileCommentController);
	controllers.controller("profilePostController", profilePostController);
	controllers.controller("profileController", profileController);
	controllers.controller("profileUpvoteController", profileUpvoteController);
	
	controllers.controller("menuController", menuController);

})