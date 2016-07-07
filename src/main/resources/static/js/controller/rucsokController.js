define(
		[

        ], function() {
			
	rucsokController.$inject = ["rucsokService"]		
			
	function rucsokController(rucsokService) {
		var vm = this;

		vm.rucsoks = [];
		
		
		vm.rucsok = rucsok;
		
		function rucsok() {
			rucsokService.getRucsok().then(function(data) {
				vm.rucsoks = data;
			});
		}
	}
	
	return rucsokController;
})