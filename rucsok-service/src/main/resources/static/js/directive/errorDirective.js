define(["js/controller/errorController.js"], function(errorController) {
	function error() {
		return {
			"restrict" : 'E',
			'controller' : errorController,
			'bindToController': true,
			'controllerAs' : 'vm',
			'templateUrl' : 'js/templates/error.html'
		}
	}
	
	return error;
});