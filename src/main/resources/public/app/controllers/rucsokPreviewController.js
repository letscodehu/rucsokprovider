define([ "jquery" ], function($) {

	rucsokPreviewController.$inject = [ "$scope"]

	function rucsokPreviewController($scope) {

		var showRucsok = false;
		$scope.showRucsokPreview = function(){
			return showRucsok;
		};
		
		$scope.rucsok = {
			title: "rücsök example",
			imageUrl: "http://localhost:8080/images/rucsi.png",
			videoUrl: "",
			link: "http://localhost:8080",
			hasVideo: function(){
				return typeof $scope.rucsok.videoUrl !== 'undefined' && $scope.rucsok.videoUrl.length>0;
			}
		};

		var updateListener = $scope.$on('rucsok.preview', function(event, data) {
			copyNewRucsok(data);
			showRucsok = true;			
		});
		
		function copyNewRucsok(data){
			$scope.rucsok.title 	= data.title || "";
			$scope.rucsok.imageUrl 	= data.imageUrl || "";
			$scope.rucsok.videoUrl 	= data.videoUrl || "";
			$scope.rucsok.link 		= data.link || "";
		}

		$scope.$on('$destroy', function() {
			updateListener();
		});

	}

	return rucsokPreviewController;
});