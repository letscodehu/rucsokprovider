define([], function() {

	singleController.$inject = [ "$scope", "$state", "rucsokService",
			"$stateParams", '$ionicHistory', '$document',  'rucsokModelService'];

	function singleController($scope, $state, rucsokService, $stateParams,
			$ionicHistory, $document, rucsokModelService) {
		var vm = this;
		$scope.item = {};

		$scope.onKeyUp = onKeyUp;
		$scope.swipeLeft = swipeLeft;
		$scope.swipeRight = swipeRight;
		$scope.voteRucsok = voteRucsok;
		$scope.alreadyVotedClass = '';

		$scope.backButton = function() {
			if (canGoBackInHistory()) {
				goBackInHistory();
			} else {
				goToDashboard();
			}
		};
		
		function goToDashboard() {
			$state.go('app.tabs');
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
			$state.go('single', {
				id : $scope.next
			});
		}

		function swipeRight() {
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
			$scope.item = rucsokModelService.createRucsokFromRequest(data.current);
			$scope.next = data.nextId;
			$scope.prev = data.previousId;
		}

		function voteRucsok(where) {
			var voteObject = {
				'rucsokid': $stateParams.id,
				'voteType': where
			}
			switch(where) {
				case 'UP':
					// Backend logic copy here(inc/dec value)
					$scope.item.vote++;
					$scope.item.alreadyVoted = 'UP';
					break;
				case 'DOWN':
					// Backend logic copy here(inc/dec value)
					$scope.item.vote--;
					$scope.item.alreadyVoted = 'DOWN';
					break;
				default: break;
			}
			rucsokService.voteRucsok(voteObject).then(function() {
				$scope.item.alreadyVoted = where;
			}, function() {
				$scope.item.alreadyVoted = null;
			});
		}

		init();

	}

	return singleController;
})