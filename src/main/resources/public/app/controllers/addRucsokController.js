define([ "jquery" ], function($) {

	addRucsokController.$inject = [ "$scope", "rucsokService",
			"addRucsokFormService", "crawlRucsokService", "$state" ]

	function addRucsokController($scope, rucsokService, addRucsokFormService,
			crawlRucsokService, $state) {

		$scope.showAddRucsokForm = showAddRucsokForm;
		$scope.hideAddRucsokForm = hideAddRucsokForm;
		$scope.crawlNewRucsok = crawlNewRucsok;
		$scope.formData = {
			url : ""
		};

		function showAddRucsokForm() {
			return addRucsokFormService.isShow();
		}

		function hideAddRucsokForm() {
			addRucsokFormService.hideView();
			if (!addRucsokFormService.isShow()) {
				$scope.url = "";
			}
		}

		function crawlNewRucsok() {
			crawlRucsokService
				.crawlUrl($scope.formData.url)
				.then(function(data) {
					$scope.$broadcast('rucsok.preview', data);
				}, showError);
		}

		function showError() {
			$scope.urlErrorClass = 'alert-danger';
		}

	}

	return addRucsokController;
});