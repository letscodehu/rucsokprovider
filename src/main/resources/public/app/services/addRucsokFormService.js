define([], function() {
		
	function addRucsokFormService() {

		var vm = this;
		vm.show = false;
		
		function toggleView(){
			vm.show = !vm.show;
		}
			
		function hideView(){
			vm.show = false;
		}
		
		function showView(){
			vm.show = true;
		}
		
		function isShow(){
			return vm.show;
		}
			
		return {
			toggleView : toggleView,
			hideView : hideView,
			showView : showView,
			isShow: isShow
		}
	};
	
	return addRucsokFormService;
});