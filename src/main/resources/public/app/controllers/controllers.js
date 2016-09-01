define([ "controllers/rucsokController",
         "controllers/loginController", 
         "controllers/errorController", 
         "controllers/singleController",
         "controllers/userProfileController",
         "controllers/addRucsokController",
         "controllers/rucsokPreviewController",
         "controllers/dashboardController",
         "ionic"
        ], function(
                        rucsokController, 
                        loginController, 
                        errorController, 
                        singleController,
                        userProfileController,
                        addRucsokController, 
                        rucsokPreviewController,
                        dashboardController
                        ) {

        var controllers = angular.module("controllers", []);	

        controllers.controller("addRucsokController", addRucsokController);
        controllers.controller("rucsokPreviewController", rucsokPreviewController);
        controllers.controller("singleController", singleController);
        controllers.controller("userProfileController", userProfileController);
        controllers.controller("loginController", loginController);
        controllers.controller("dashboardController", dashboardController);
        controllers.controller("rucsokController", rucsokController);
        controllers.controller("errorController", errorController);
})