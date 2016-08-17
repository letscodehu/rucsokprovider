define([], function() {

	menuController.$inject = ["$scope", "userProfileService", "loginService", "$state"];
	
	function menuController($scope, userProfileService, loginService, $state) {

		$scope.logout = logout;
		$scope.isLoggedIn = userProfileService.isLoggedIn;
		$scope.getUsername = userProfileService.getUsername;
		$scope.goToLogin = goToLogin;

		function goToLogin() {
			$state.go('app.login');
		}

		function logout() {
			
			loginService.logout().then(function() {
				$state.go('app.dashboard');
			});
		}

		
	}

	return menuController;
})