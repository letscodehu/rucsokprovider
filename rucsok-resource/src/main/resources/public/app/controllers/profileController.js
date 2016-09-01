define([], function() {

	profileController.$inject = ["$rootScope", "$scope", "userProfileService", "$ionicHistory", "$ionicTabsDelegate", "$timeout"];
	
	function profileController($rootScope, $scope, userProfileService, $ionicHistory, $ionicTabsDelegate, $timeout) {
        $timeout(function() {
            $ionicTabsDelegate.select($rootScope.activeProfileTabIndex);
        }, 100);
	}

	return profileController;
})