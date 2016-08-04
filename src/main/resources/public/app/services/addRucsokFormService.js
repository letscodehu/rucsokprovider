define([], function() {
		
	function addRucsokFormService() {

		var vm = this;
		vm.show = false;
		
		function toggleView(){
			console.log(vm.show)
			vm.show = !vm.show;
		}
				
		function isShow(){
			return vm.show;
		}
			
		return {
			toggleView : toggleView,
			isShow: isShow
		}
	};
	
	return addRucsokFormService;
});