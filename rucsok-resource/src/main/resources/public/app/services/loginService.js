define([ 'jquery' ], function($) {

	loginService.$inject = [ '$http', '$q', '$rootScope'];

	function loginService($http, $q, $rootScope) {

		var vm = this;
				
		function loginUser(username, password) {
			var deferred = $q.defer();

			$http({
				'url' : '/login',
				'method' : 'POST',
				'data' : {
					'username' : username,
					'password' : password,
				},
				transformRequest: function(obj) {
			        var str = [];
			        for(var p in obj)
			        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			        return str.join("&");
			    },
				headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			}).then(function(resp) {
				$rootScope.$emit('loggedin', resp.data);
				deferred.resolve();
			}, function(err) {
				deferred.reject(err);
			});

			return deferred.promise;
		}
		
		function logout() {
			var deferred = $q.defer();
			$http({
				'url' : '/logout',
				'method' : 'POST'
			}).then(function() {
				$rootScope.$emit("logout");
				deferred.resolve();
			})
			
			return deferred.promise;
		}

		return {
			logout : logout,
			loginUser : loginUser
		}
	}

	return loginService;
});