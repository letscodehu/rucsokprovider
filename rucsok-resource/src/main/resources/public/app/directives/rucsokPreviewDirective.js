define(["controllers/rucsokPreviewController"], function(rucsokPreviewController) {
	
	function rucsokPreview() {
		return {
			"restrict" : 'E',
			'controller' : rucsokPreviewController,
			'bindToController': true,
			'controllerAs' : 'vm',
			'templateUrl' : 'app/templates/rucsokPreview.html'
		}
	}
	
	return rucsokPreview;
});