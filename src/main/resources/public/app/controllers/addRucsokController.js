define([ "jquery" ], function($) {

	addRucsokController.$inject = [ "$scope", "rucsokService",
			"addRucsokFormService", "$state" ]

	function addRucsokController($scope, rucsokService, addRucsokFormService,
			$state) {

		$scope.showAddRucsokForm = toggleForm;
		$scope.hideAddRucsokForm = toggleForm;

		function toggleForm() {
			return addRucsokFormService.isShow();
		}

	}

	return addRucsokController;
});