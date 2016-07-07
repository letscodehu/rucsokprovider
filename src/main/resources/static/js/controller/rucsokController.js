define(
		[
		 "jquery"
        ], function($) {
			
	rucsokController.$inject = ["rucsokService"]		
			
	function rucsokController(rucsokService) {
		var vm = this;

		vm.rucsoks = [];
		
		vm.dirty = true;
		vm.preload = false;
		vm.addRucsok = addRucsok;
		vm.checkRucsok = checkRucsok;
		vm.showAddModal = showAddModal;
		vm.rucsok = rucsok;
		
		function addRucsok() {
			vm.preload = true;
			rucsokService.addRucsok(vm.rucsok).then(function(){
				vm.preload = false;
				rucsokService.getRucsok().then(function(data) {
					vm.rucsoks = data;
					closeAddModal();
				});	
			})
		}
		
		function checkRucsok() {
			rucsokService.checkRucsok(vm.url).then(function(data) {
				vm.dirty = false;
				vm.rucsok = data;
			});
		}		
		
		function showAddModal() {
			$("#rucsok-modal").modal("show");
		}
		
		function closeAddModal() {
			$("#rucsok-modal").modal("hide");
		}
		
		function rucsok() {
			rucsokService.getRucsok().then(function(data) {
				vm.rucsoks = data;
				if (vm.rucsoks.length == 0) {
					showAddModal();
				}
			});
		}
	}
	
	return rucsokController;
})