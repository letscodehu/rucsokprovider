define(['base/app/services/rucsokService', 'ionic', 'angular-mocks'], function(rucsokService, angular) {
	describe('rucsok', function() {
		
		 // Here we register the function returned by the myFilter AMD module
	      beforeEach(angular.mock.module(function($filterProvider) {
	        $filterprovider.register('myFilter', myFilter);
	      }));
		
		
		it('should not be null', inject(function() {
			rucsokService.getRucsok();
		}));
		
	})
	
});