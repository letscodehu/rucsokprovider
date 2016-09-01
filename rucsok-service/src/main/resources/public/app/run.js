define([
        'app'
        ], function (app) {
	'use strict';
	// the run blocks
	app.run([
	         '$ionicPlatform', '$rootScope', '$http', 'storeService',
	         function ($ionicPlatform, $rootScope, $http, storeService) {
	        	 
	        	 
	        	 storeService.getAccessToken().then(function(access_token){
	        		 if (access_token) {
	        			 $http.defaults.headers.common.Authorization = 
		        			 'Bearer ' + access_token;	 
	        		 }
	        		 $rootScope.$emit("tokenchecked");
	        	 }) 
	        	 
	        	 $rootScope.$on("loggedin", function(event, tokenData) {
	        		 $http.defaults.headers.common.Authorization = 
	        			 'Bearer ' + tokenData.access_token;
	        	 });
	        	 
	        	 $rootScope.$on("logout", function(event, tokenData) {
	        		 delete $http.defaults.headers.common.Authorization;
	        	 });


	        	 $ionicPlatform.ready(function() {
	        		 // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
	        		 // for form inputs)
	        		 if (window.cordova && window.cordova.plugins.Keyboard) {
	        			 cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
	        			 cordova.plugins.Keyboard.disableScroll(true);
	        		 }
	        		 if (window.StatusBar) {
	        			 // org.apache.cordova.statusbar required
	        			 StatusBar.styleDefault();
	        		 }
	        	 });
	         }
	         ]);
});
