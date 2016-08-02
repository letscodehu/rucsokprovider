define([], function() {

	singleController.$inject = ["$scope","$state", "rucsokService" , "$stateParams"];
	
	function singleController($scope, $state, rucsokService, $stateParams) {
		var vm = this;
		$scope.item = {};
		
		$scope.onKeyUp = onKeyUp;
		$scope.swipeLeft = swipeLeft;
		$scope.swipeRight = swipeRight;
		
		
		function onKeyUp($event) {
			console.log("befut");
			switch ($event.keyCode) {
			case '37' : swipeLeft();
				break;
			case '39' : swipeRight();
				break;
			}
		}
		
		function swipeLeft() {
			getItem($scope.prev);
		}
		
		function swipeRight() {
			getItem($scope.next);
		}
		
		
		function init() {
			getItem($stateParams.id);
		}
		
		function getItem(id) {
			rucsokService.getItem(id)
			.then(function(data) {
				$scope.item = data.rucsok;
				$scope.next = data.next;
				$scope.prev = data.previous;
			});
		}
		
		init();
		
	}

	return singleController;
})