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
		
		function checkRucsok(url) {
			var deferred = $q.defer();
			// viva la promise, peti
			$http({
				"url" : "/rucsok", 
				"method" : "POST",
				"data" : {"url": url},
			}).then(function(resp) {
				deferred.resolve(resp.data);
			});
			
			return deferred.promise;
		}
		
		function addRucsok(rucsok) {
			var deferred = $q.defer();
			// viva la promise, peti
			$http({
				"url" : "/rucsok", 
				"method" : "PUT",
				"data" : {"rucsok" : rucsok},
			}).then(function(resp) {
				deferred.resolve(resp.data);
			});
			
			return deferred.promise;
		}
				
		return {
			checkRucsok : checkRucsok,
			getRucsok : getRucsok
		}
	};
	
	return rucsokService;
});