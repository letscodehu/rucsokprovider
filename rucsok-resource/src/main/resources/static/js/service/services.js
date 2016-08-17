define(["angular", "js/service/rucsokService.js"], function(angular, rucsokService) {
	
	var services = angular.module("services", []);
	
	services.factory("rucsokService", rucsokService);
	
	return services;
	
});