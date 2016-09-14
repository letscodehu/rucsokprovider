define([
        'app', 'localforage'
        ], function (app, localForage) {
	'use strict';
	
	// initial configuration of localForage
	localForage.config({
		name : 'rucsokreceiver',
		version : 1.0,
		storeName : 'rucsok-store'
	});
	
	app.config(["ionGalleryConfigProvider", "$ionicConfigProvider", "$httpProvider",  
        function (ionGalleryConfigProvider, $ionicConfigProvider, $httpProvider) {

        // Disable global view caching, caused a state-not-change-but-url-does anomaly
        $ionicConfigProvider.views.maxCache(0);

        $httpProvider.interceptors.push(function(){
            return {
                request: function(config) {
                    if (/\.\D{2,4}$/.test(config.url) === false) {
                        config.url = 'http://10.0.14.85:3333' + config.url;
                    }
                    return config;
                }
            };
        });

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
