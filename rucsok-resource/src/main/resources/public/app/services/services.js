define([ "app/services/rucsokService.js",
         "app/services/addRucsokFormService.js",
         "app/services/crawlRucsokService.js",
         "app/services/loginService.js",
         "app/services/authResolverFactory.js",
         "app/services/userProfileService.js",
         "app/services/rucsokModelService.js",
         "app/services/randomPunService.js",
         "app/services/storeService.js",
         "ionic"], function(
        		 rucsokService, 
        		 addRucsokFormService, 
        		 crawlRucsokService,
        		 loginService,
        		 authResolverFactory,
        		 userProfileService,
        		 rucsokModelService,
                         randomPunService,
        		 storeService
        		 ) {
	
	var services = angular.module("services", []);
	services.factory("rucsokModelService", rucsokModelService);
	services.factory("rucsokService", rucsokService);
	services.factory("addRucsokFormService", addRucsokFormService);
	services.factory("crawlRucsokService", crawlRucsokService);
	services.factory("loginService", loginService);
	services.factory("storeService", storeService);
	services.factory("authResolverFactory", authResolverFactory);
        services.factory("randomPunService", randomPunService);
	services.factory("userProfileService", userProfileService);
	return services;
	
});
