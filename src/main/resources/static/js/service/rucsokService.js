define([], function() {
		
	rucsokService.$inject = ["$http", "$q"];
	
	function rucsokService($http, $q) {

		var vm = this;

		
		function getRucsok() {
			
			var deferred = $q.defer();
			// viva la promise, peti
			$http.get("/rucsok").then(function(resp) {
				deferred.resolve(resp.data);
			});
			
			return deferred.promise;
		}
				
		return {
			getRucsok : getRucsok
		}
	};
	
	return rucsokService;
});