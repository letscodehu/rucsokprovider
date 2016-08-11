define([ 'jquery' ], function($) {

	rucsokController.$inject = [ '$scope', 'rucsokService',
			'addRucsokFormService', '$state', 'authResolverFactory', 'userProfileService' ]

	function rucsokController($scope, rucsokService, addRucsokFormService,
			$state, authResolverFactory, userProfileService) {

		$scope.rucsoks = [];
		$scope.addRucsok = addRucsok;
		$scope.showRucsok = showRucsok;
		$scope.isLoggedIn = userProfileService.isLoggedIn();
		$scope.goToLogin = goToLogin;

		refresh();

		var updateListener = $scope.$on('rucsok.added', function(event) {
			vm.refresh();
		});

		$scope.$on('$destroy', function() {
			updateListener();
		});

		function refresh() {
			rucsokService.getRucsok().then(function(data) {
				$scope.rucsoks = data;
			});
		}

		function visit(url) {
			var win = window.open(url, '_blank');
			win.focus();
		}

		function addRucsok() {
			addRucsokFormService.showView();
		}

		function showRucsok(item) {
			$state.go('single', {
				id : item.id
			});
		}

		function goToLogin() {
			$state.go('login');
		}

	}

	return rucsokController;
})