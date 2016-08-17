define([ 'jquery' ], function($) {

	loginService.$inject = [ '$http', '$q', 'userProfileService', '$state'];

	function loginService($http, $q, userProfileService, $state) {

		var vm = this;
		vm.access_token = null;
		vm.refresh_token = null;
		
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
				vm.access_token = resp.data.access_token;
				vm.refresh_token = resp.data.refresh_token;
				$http.defaults.headers.common.Authorization = 
			          'Bearer ' + vm.access_token;
				deferred.resolve();
				userProfileService.onNotify();
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
				userProfileService.onNotify();
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