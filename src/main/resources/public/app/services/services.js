define([ "app/services/rucsokService.js",
         "app/services/addRucsokFormService.js",
         "app/services/crawlRucsokService.js",
         "ionic"], function(
        		 rucsokService, 
        		 addRucsokFormService, 
        		 crawlRucsokService
        		 ) {
	
	var services = angular.module("services", []);
	
	services.factory("rucsokService", rucsokService);
	services.factory("addRucsokFormService", addRucsokFormService);
	services.factory("crawlRucsokService", crawlRucsokService);
	
	return services;
	
});
