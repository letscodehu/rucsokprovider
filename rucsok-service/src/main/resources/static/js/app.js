define([ "angular", "js/controller/controllers.js",
		"js/directive/directives.js", "js/service/services.js" ], function(
		angular) {

	var app = new application();

	function application() {

	}

	application.prototype.init = function() {

		var app = angular.module("rucsok", [ "controllers", "directives",
				"services", ]);

		app.config([ '$httpProvider', function($httpProvider) {

			 //Authorization
	        $httpProvider.interceptors.push(['$q', '$location',
	            function($q,  $location) {	  
	        	
		        	var csrf = $("[name='_csrf']").val();            	
                 		        	  
	                return {
	                    'request': function(config) {	                    	
	                        config.headers = config.headers || {};	  
	                        console.log(config.headers)
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

		}]);
		
		angular.bootstrap(document, [ 'rucsok' ]);

	}

	app.init();

});