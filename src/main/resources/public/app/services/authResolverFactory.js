define([], function() {

	authResolver.$inject = [ '$q', '$rootScope', '$state', '$http' ];

	function authResolver($q, $rootScope, $state, $http) {
		
		function resolve(){
			var deferred = $q.defer();
			var unwatch = $rootScope.$watch('currentUser', function(currentUser) {
				if (angular.isDefined(currentUser)) {
					if (currentUser) {
						deferred.resolve(currentUser);
					} else {
						deferred.reject();
						$state.go('login');
					}
					unwatch();
				}
			});
			return deferred.promise;
		}	

		function isLoggedIn(){
			var deferred = $q.defer();
			var unwatch = $rootScope.$watch('currentUser', function(currentUser) {
				if (angular.isDefined(currentUser) &&  currentUser) {
						deferred.resolve(currentUser);
						deferred.reject();
				}
				deferred.reject();
				unwatch();
			});
			return deferred.promise;
		}	
		
		return {
			resolve : resolve,
			isLoggedIn : isLoggedIn
		}
	}

	return authResolver;
});