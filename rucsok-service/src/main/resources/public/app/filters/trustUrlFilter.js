define(["ionic"],
		function() {
	
	trustUrlFilter.$inject = ["$sce"]
	
	function trustUrlFilter($sce) {
		
		 return function (recordingUrl) {
	           return $sce.trustAsResourceUrl(recordingUrl);
	       };
		
	}
	
	return trustUrlFilter;
	
});