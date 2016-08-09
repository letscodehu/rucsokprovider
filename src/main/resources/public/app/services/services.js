define([ "app/services/rucsokService.js",
         "app/services/addRucsokFormService.js",
         "app/services/crawlRucsokService.js",
         "app/services/loginService.js",
         "ionic"], function(
        		 rucsokService, 
        		 addRucsokFormService, 
        		 crawlRucsokService,
        		 loginService
        		 ) {
	
	var services = angular.module("services", []);
	
	services.factory("rucsokService", rucsokService);
	services.factory("addRucsokFormService", addRucsokFormService);
	services.factory("crawlRucsokService", crawlRucsokService);
	services.factory("loginService", loginService);
	
	return services;
	
});
