define([], function() {

	userProfile.$inject = [ '$q', '$rootScope', '$state', '$http', 'authService' ];

	function userProfile($q, $rootScope, $state, $http, authService) {
		
		var userProfile = null;
		
		if(!isLoggedIn()){
			 loadUserProfile().then(function(data){
				 userProfile = data;
			 });
		}
		
		function isLoggedIn() {
			return null !== userProfile;
		}
		
		function onNotify() {
			 loadUserProfile().then(function(data){
				 userProfile = data;
			 });
		}
		
		function getUsername() {
			if (typeof userProfile != null) {
				return userProfile.username;
			}
			return null;
		}
		
		function loadUserProfile(){
			var deferred = $q.defer();
			$http.get("/profile/").then(function(resp) {
				deferred.resolve(resp.data);
			});
			
			return deferred.promise;			
		}
		
		return {
			loadUserProfile : loadUserProfile,
			isLoggedIn: isLoggedIn,
			onNotify : onNotify,
			getUsername : getUsername
		}
	}

	return userProfile;
});