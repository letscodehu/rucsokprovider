define([ "app/services/rucsokService.js",
         "app/services/addRucsokFormService.js",
         "app/services/crawlRucsokService.js",
         "app/services/loginService.js",
         "app/services/authResolverFactory.js",
         "app/services/userProfileService.js",
         "app/services/csrfRefreshService.js",
         "ionic"], function(
        		 rucsokService, 
        		 addRucsokFormService, 
        		 crawlRucsokService,
        		 loginService,
        		 authResolverFactory,
        		 userProfileService,
        		 csrfRefreshService
        		 ) {
	
	var services = angular.module("services", []);
	
	services.factory("rucsokService", rucsokService);
	services.factory("addRucsokFormService", addRucsokFormService);
	services.factory("crawlRucsokService", crawlRucsokService);
	services.factory("loginService", loginService);
	services.factory("authResolverFactory", authResolverFactory);
	services.factory("userProfileService", userProfileService);
	services.factory("csrfRefreshService", csrfRefreshService);
	return services;
	
});
