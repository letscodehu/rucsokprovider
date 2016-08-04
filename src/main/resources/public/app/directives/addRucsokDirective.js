define(["controllers/addRucsokController"], function(addRucsokController) {
	
	function addRucsok() {
		return {
			"restrict" : 'E',
			'controller' : addRucsokController,
			'bindToController': true,
			'controllerAs' : 'vm',
			'templateUrl' : 'app/templates/addRucsok.html'
		}
	}
	
	return addRucsok;
});