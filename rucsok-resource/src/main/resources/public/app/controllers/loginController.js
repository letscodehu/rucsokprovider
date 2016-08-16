define([], function() {

	loginController.$inject = [ '$scope', 'loginService', '$ionicPopup',
			'$state' ]

	function loginController($scope, loginService, $ionicPopup, $state) {

		$scope.data = {};
		$scope.login = login;

		function login() {
			loginService.loginUser($scope.data.username, $scope.data.password)
					.then(function success(data) {
						$state.go('dashboard');
					}, function failure(data) {
						$state.go('login');
						var alertPopup = $ionicPopup.alert({
							title : 'Login failed!',
							template : 'Please check your credentials!'
						});
					});
		}

	}

	return loginController;
});