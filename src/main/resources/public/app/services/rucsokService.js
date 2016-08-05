define([], function() {
		
	rucsokService.$inject = ["$http", "$q"];
	
	function rucsokService($http, $q) {

		var vm = this;
		
		function getRucsok() {
			
			var deferred = $q.defer();
			// viva la promise, peti
			$http.get("/rucsok").then(function(resp) {
				
				var data = [];
				resp.data.forEach(function(item) {
					data.push({
						'src' : item.imageUrl,
						'video-src' : item.videoUrl,
						'sub' : item.title,
						"link" : item.link,
						'id' : item.id,
					})
				});
				
				deferred.resolve(data);
			});
			
			return deferred.promise;
		}
		
		function getItem(id) {
			var deferred = $q.defer();

			$http.get("/rucsok/" + id).then(function(resp) {
				deferred.resolve(resp.data);
			});
			
			return deferred.promise;
		}
				
		return {
			getItem : getItem,
			getRucsok : getRucsok
		}
	};
	
	return rucsokService;
});