define([ "filters/trustUrlFilter",
         "ionic"
        ], function(
        		trustUrlFilter
        		) {
	
	var filters = angular.module("filters", []);	
	
	filters.filter("trustUrl", trustUrlFilter)
	
	
})