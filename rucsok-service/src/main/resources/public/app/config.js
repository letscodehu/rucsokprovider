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
	
	app.config(["ionGalleryConfigProvider", 
	            function (ionGalleryConfigProvider) {

						
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
