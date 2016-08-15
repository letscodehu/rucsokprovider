define([ 'jquery' ], function($) {

	loginService.$inject = [ '$http', '$q' ];

	function loginService($http, $q) {

		var vm = this;
		
		function loginUser(username, password) {
			var deferred = $q.defer();

			var csrfToken = $("[name='_csrf']").val();

			$http({
				'url' : '/login',
				'method' : 'POST',
				'data' : {
					'sec-user' : username,
					'sec-password' : password,
					'_csrf' : csrfToken
				},
				transformRequest: function(obj) {
			        var str = [];
			        for(var p in obj)
			        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			        return str.join("&");
			    },
				headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			}).then(function(resp) {
				deferred.resolve(resp.data);
			}, function(err) {
				deferred.reject(err);
			});

			return deferred.promise;
		}

		return {
			loginUser : loginUser
		}
	}

	return loginService;
});