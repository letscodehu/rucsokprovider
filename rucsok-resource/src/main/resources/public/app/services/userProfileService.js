define([], function() {

	userProfile.$inject = [ '$q', '$rootScope', '$state', '$http' ];

	function userProfile($q, $rootScope, $state, $http) {
		
		var userProfile = null;
		
		$rootScope.$on("tokenchecked", function(){
			 loadUserProfile().then(function(data){
				 userProfile = data;
			 }, function() {
				 userProfile = null;
			 });
		});
		
		function isLoggedIn() {
			return null !== userProfile;
		}
		
		var onLogin = $rootScope.$on('loggedin', function(event) {
			 loadUserProfile().then(function(data){
				 userProfile = data;
			 });
		});
		
		var onLogout = $rootScope.$on('logout', function() {
			 userProfile = null;
		});
		
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
			}, function(resp) {
				deferred.reject(resp.data);
			});
			
			return deferred.promise;			
		}
		
		return {
			loadUserProfile : loadUserProfile,
			isLoggedIn: isLoggedIn,
			getUsername : getUsername
		}
	}

	return userProfile;
});