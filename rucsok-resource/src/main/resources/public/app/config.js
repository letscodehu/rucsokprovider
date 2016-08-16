define([
        'app'
        ], function (app) {
	'use strict';
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
