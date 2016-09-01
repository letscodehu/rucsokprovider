define([ "jquery" ], function($) {

	rucsokPreviewController.$inject = [ "$scope", 'rucsokModelService']

	function rucsokPreviewController($scope, rucsokModelService) {

		var showRucsok = false;
		$scope.showRucsokPreview = function(){
			return showRucsok;
		};
		
		$scope.rucsok = rucsokModelService.createEmptyRucsok();

		var updateListener = $scope.$on('rucsok.preview', function(event, data) {
			$scope.rucsok = rucsokModelService.createRucsokFromRequest(data);
			showRucsok = true;			
		});
		
		$scope.$on('$destroy', function() {
			updateListener();
		});

	}

	return rucsokPreviewController;
});