define([ "jquery" ], function($) {

	addRucsokController.$inject = [ "$scope", "rucsokService",
			"addRucsokFormService", "crawlRucsokService", "$state", '$q',
			'authResolverFactory' ]

	function addRucsokController($scope, rucsokService, addRucsokFormService,
			crawlRucsokService, $state, $q, authResolverFactory) {

		// public

		$scope.showAddRucsokForm = showAddRucsokForm;
		$scope.hideAddRucsokForm = hideAddRucsokForm;
		$scope.crawlNewRucsok = crawlNewRucsok;
		$scope.addNewRucsok = addNewRucsok;
		$scope.formData = {
			url : ""
		};

		// private
		
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
					}, showError('fos url'));
		}

		function addNewRucsok() {
			createRucsok().then(function(newRucsok) {
				addRucsokFormService.addRucsok(newRucsok).then(function() {
					$scope.$broadcast('rucsok.added');
					resetCurrentRucsok();
					
					$state.go("app.dashboard");
					// redirect
				}, showError());
			});
		}

		function createRucsok() {
			var deferred = $q.defer();

			if (null === currentRucsok) {
				crawlRucsokService.crawlUrl($scope.formData.url).then(
						deferred.resolve, showError('fos url'));
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