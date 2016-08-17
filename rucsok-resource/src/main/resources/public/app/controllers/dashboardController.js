define([ 'jquery' ], function($) {

	dashboardController.$inject = [ '$scope', 'rucsokService',
			'addRucsokFormService', '$state']

	function dashboardController($scope, rucsokService, addRucsokFormService,
			$state) {

		$scope.rucsoks = [];
		$scope.addRucsok = addRucsok;
		$scope.showRucsok = showRucsok;

		refresh();

		var updateListener = $scope.$on('rucsok.added', function(event) {
			refresh();
		});

		$scope.$on('$destroy', function() {
			updateListener();
		});
		
				
		function refresh() {
			rucsokService.getRucsok().then(function(data) {
				$scope.rucsoks = data;
			});
		}

		function addRucsok() {
			$state.go('app.add');
		}

		function showRucsok(item) {
			$state.go('app.single', {
				id : item.id
			});
		}
		
	}

	return dashboardController;
})