define([ "app/services/rucsokService.js", "ionic"], function(rucsokService) {
	
	var services = angular.module("services", []);
	
	services.factory("rucsokService", rucsokService);
	
	return services;
	
});
