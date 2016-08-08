define([], function() {

	singleController.$inject = [ "$scope", "$state", "rucsokService",
			"$stateParams", '$ionicHistory', '$document' ];

	function singleController($scope, $state, rucsokService, $stateParams,
			$ionicHistory, $document) {
		var vm = this;
		$scope.item = {};

		$scope.onKeyUp = onKeyUp;
		$scope.swipeLeft = swipeLeft;
		$scope.swipeRight = swipeRight;

		$scope.backButton = function() {
			if (canGoBackInHistory()) {
				goBackInHistory();
			} else {
				goToDashboard();
			}
		};
		
		function goToDashboard() {
			$state.go('dashboard');
		}

		function goBackInHistory() {
			$ionicHistory.goBack();
		}

		function canGoBackInHistory() {
			return null !== $ionicHistory.viewHistory().backView;
		}

		function onKeyUp($event) {
			switch ($event.keyCode) {
			case 37:
				swipeRight();
				console.log($scope.prev);
				break;
			case 39:
				swipeLeft();
				break;
			}
		}

		function swipeLeft() {
			console.log($scope.raw);
			$state.go('single', {
				id : $scope.next
			});
		}

		function swipeRight() {
			console.log(  $scope.raw);
			$state.go('single', {
				id : $scope.prev
			});
		}

		function init() {
			getItem($stateParams.id);
		}

		function getItem(id) {

			if (typeof id === 'undefined') {
				return;
			}

			rucsokService.getItem(id).then(assignRucsokToScope);

		}

		function assignRucsokToScope(data) {
			$scope.raw = data;
			$scope.item = data.current;
			$scope.next = data.nextId;
			$scope.prev = data.previousId;
		}

		init();

	}

	return singleController;
})