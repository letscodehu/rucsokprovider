define([ "filters/trustUrlFilter",
         "filters/removeNumSign",
         "ionic"
        ], function(
        		trustUrlFilter,
                removeNumSign
        		) {
	
	var filters = angular.module("filters", []);	
	
	filters.filter("trustUrl", trustUrlFilter)
	filters.filter("removeNumSign", removeNumSign)
	
})