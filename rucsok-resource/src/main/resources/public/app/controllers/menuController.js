define([], function() {

	menuController.$inject = ["$scope", "userProfileService", "loginService", "$state", "$ionicSideMenuDelegate"];
	
	function menuController($scope, userProfileService, loginService, $state, $ionicSideMenuDelegate) {

		$scope.logout = logout;
		$scope.isLoggedIn = userProfileService.isLoggedIn;
		$scope.getUsername = userProfileService.getUsername;
		$scope.goToLogin = goToLogin;
		$scope.profile = profile;
		$scope.isDashboard = isDashboard;
		$scope.home = home;
		
		function home() {
			$state.go("app.tabs");
			$ionicSideMenuDelegate.toggleLeft();
		}
		
		function goToLogin() {
			$state.go('app.login');
			$ionicSideMenuDelegate.toggleLeft();
		}

		function logout() {
			
			loginService.logout().then(function() {
				$state.go('app.tabs');
			});
		}
		
		function isDashboard() {
			return $state.is('app.tabs');
		}

		function profile() {
			$state.go("app.profile");
			$ionicSideMenuDelegate.toggleLeft();
		}
	}

	return menuController;
})