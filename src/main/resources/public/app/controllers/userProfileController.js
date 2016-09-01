define([], function() {

    userProfileController.$inject = [ "$scope", "$state", "rucsokService",
            "$stateParams", '$ionicHistory', '$document'];

    function userProfileController($scope, $state, rucsokService, $stateParams,
            $ionicHistory, $document) {
        var vm = this;
        $scope.title = 'Profile';
        $scope.item = {};

        $scope.onKeyUp = onKeyUp;
        $scope.swipeLeft = swipeLeft;
        $scope.swipeRight = swipeRight;

        $scope.backButton = function() {
            if (canGoBackInHistory()) {
                goBackInHistory();
            } else {
                goToDashboard();
            }
        };

        function goToDashboard() {
            $state.go('dashboard');
        }

        function goBackInHistory() {
            $ionicHistory.goBack();
        }

        function canGoBackInHistory() {
            return null !== $ionicHistory.viewHistory().backView;
        }

        function onKeyUp($event) {
            switch ($event.keyCode) {
            case 37:
                swipeRight();
                break;
            case 39:
                swipeLeft();
                break;
            }
        }

        function swipeLeft() {
            $state.go('profile', {
                id : $scope.next
            });
        }

        function swipeRight() {
            $state.go('profile', {
                id : $scope.prev
            });
        }

        function init() {
            getProfile($stateParams.id);
        }

        function getProfile(id) {
            // Send request here
        }

        init();

    }

    return userProfileController;
})