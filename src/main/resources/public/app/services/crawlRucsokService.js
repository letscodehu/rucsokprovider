define([], function() {

	crawlRucsokService.$inject = [ "$http", "$q" ];

	function crawlRucsokService($http, $q) {

		function checkRucsokPromiseFactory(url) {
			var deferred = $q.defer();
			
			if (!checkUrl(url)) {
				deferred.reject("fos url");
			}else{
				postUrl(deferred, url);
			}			
		
			return deferred.promise;
		}

		function postUrl(deferred, url) {
			$http({
				"url" : "/check-rucsok",
				"method" : "POST",
				"data" : {
					"url" : url
				},
			}).then(function(resp) {
				deferred.resolve(resp.data);
			}, function(err) {
				deferred.reject(err);
			});
		}

		function checkUrl(url) {
			return typeof url !== 'undefined' && url.substr(0, 4) === 'http';
		}

		return {
			crawlUrl : function(url) {
				return checkRucsokPromiseFactory(url);
			}

		}
	}
	;

	return crawlRucsokService;
});