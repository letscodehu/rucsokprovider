define([ "app/services/rucsokService.js",
         "app/services/addRucsokFormService.js",
         "ionic"], function(rucsokService, addRucsokFormService) {
	
	var services = angular.module("services", []);
	
	services.factory("rucsokService", rucsokService);
	services.factory("addRucsokFormService", addRucsokFormService);
	
	return services;
	
});
