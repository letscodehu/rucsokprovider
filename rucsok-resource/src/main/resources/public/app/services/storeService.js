define([ 'jquery', 'localforage' ], function($, localForage) {

	storeService.$inject = ['$q', '$rootScope'];

	function storeService($q, $rootScope) {
		
		function getAccessToken() {
			return localForage.getItem("access_token")
		}
		
		function getRefreshToken() {
			return localForage.getItem("refresh_token");
		}
		
		function setAccessToken(token) {
			localForage.setItem("access_token", token);
		}
		
		function setRefreshToken(token) {
			localForage.setItem("refresh_token", token);
		}
		
		$rootScope.$on("loggedin", function(event, data) {
			setAccessToken(data.access_token);
			setRefreshToken(data.refresh_token);
		});
		
		$rootScope.$on("logout", function(event, data) {
			setAccessToken(null);
			setRefreshToken(null);
		});

		return {
			getAccessToken : getAccessToken,
			getRefreshToken : getRefreshToken,
			setRefreshToken : setRefreshToken,
			setAccessToken : setAccessToken
		}
	}
	
	return storeService;
});