define([ "jquery" ], function($) {

	addRucsokController.$inject = [ "$scope", "rucsokService",
			"addRucsokFormService", "crawlRucsokService", "$state", '$q',
			'$rootScope' ]

	function addRucsokController($scope, rucsokService, addRucsokFormService,
			crawlRucsokService, $state, $q, $rootScope) {

		// public

		$scope.showAddRucsokForm = showAddRucsokForm;
		$scope.hideAddRucsokForm = hideAddRucsokForm;
		$scope.crawlNewRucsok = crawlNewRucsok;
		$scope.addNewRucsok = addNewRucsok;
		$scope.formData = {
			url : ""
		};

		// private
		
		var urlErrorMessage = 'Hiba az url-ben van.';
		var currentRucsok = null;

		function showAddRucsokForm() {
			return addRucsokFormService.isShow();
		}

		function resetAddRucsokFormUrl() {
			$scope.formData.url = "";
		}

		function resetCurrentRucsok() {
			currentRucsok = null;
		}

		function hideAddRucsokForm() {
			addRucsokFormService.hideView();
			if (!addRucsokFormService.isShow()) {
				resetAddRucsokFormUrl();
			}
		}

		function crawlNewRucsok() {		
			crawlRucsokService.crawlUrl($scope.formData.url).then(
					function(data) {
						$scope.$broadcast('rucsok.preview', data);
						currentRucsok = data;
					}, showError(urlErrorMessage));
		}

		function addNewRucsok() {
			createRucsok().then(function(newRucsok) {
				addRucsokFormService.addRucsok(newRucsok).then(function(data) {
					$rootScope.$broadcast('rucsok.added');
					resetCurrentRucsok();
					redirectToRucsok(data);
				}, showError());
			});
		}
		
		function redirectToRucsok(data){
			if(null!==data){
				$state.go('app.single', {
					id : data.id
				});
			}
		}

		function createRucsok() {
			var deferred = $q.defer();

			if (null === currentRucsok) {
				crawlRucsokService.crawlUrl($scope.formData.url).then(
						deferred.resolve, showError(urlErrorMessage));
			} else {
				deferred.resolve(currentRucsok);
			}
			return deferred.promise;
		}

		function showError(message) {
			return function() {
				$scope.urlErrorClass = 'alert-danger';
				$scope.errorMessage = message;
			}
		}

	}

	return addRucsokController;
});