define([], function() {

	userProfile.$inject = [ '$q', '$rootScope', '$state', '$http', 'authService' ];

	function userProfile($q, $rootScope, $state, $http, authService) {
		
		var userProfile = null;
		
		if(!isLoggedIn()){
			 loadUserProfile().then(function(data){
				 userProfile = data;
			 });
		}
		
		function isLoggedIn(){
			return null !== userProfile;
		}
		
		function loadUserProfile(){
			var deferred = $q.defer();
			
			$http.get("/profile/").then(function(resp) {
				console.log(resp);
				deferred.resolve(resp);
			});
			
			return deferred.promise;			
		}
		
		return {
			loadUserProfile : loadUserProfile,
			isLoggedIn: isLoggedIn
		}
	}

	return userProfile;
});