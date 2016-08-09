define([
  'app', 
], function (app) {
  'use strict';
   app.config(["$httpProvider", "ionGalleryConfigProvider", '$localStorage',
     function ($httpProvider, ionGalleryConfigProvider, '$localStorage' ) {
    	 //Authorization
	        $httpProvider.interceptors.push(['$q', '$location',
	            function($q,  $location) {	  
	        	
		        	var csrf = document.querySelector("[name='_csrf']").value;            	
              		        	  
	                return {
	                    'request': function(config) {	                    	
	                        config.headers = config.headers || {};	  
	                        config.headers['X-CSRF-TOKEN'] = csrf;	                       
	                        return config;
	                    },
	                    'responseError': function(response) {
	                    	console.error(response.status);
	                        if (response.status === 401 || response.status === 403) {
	                            $location.path('/error').replace();
	                        }
	                        return $q.reject(response);
	                    }
	                };
	            }
	        ]);
    	 
	        // ion gallery config 
	        ionGalleryConfigProvider.setGalleryConfig({
                action_label: 'Close',
                toggle: false,
                row_size: 3,
                fixed_row_size: false
	        });
	        
     }
   ]);
});
