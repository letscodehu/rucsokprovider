define(["js/controller/rucsokController.js"], function(rucsokController) {
	function rucsok() {
		return {
			"restrict" : 'E',
			'controller' : rucsokController,
			'bindToController': true,
			'controllerAs' : 'vm',
			'templateUrl' : 'js/templates/rucsok.html'
		}
	}
	
	return rucsok;
});