define([ 'jquery' ], function($) {

    randomPunService.$inject = [ '$http', '$q', '$ionicPopup', '$timeout' ];

    function randomPunService($http, $q, $ionicPopup, $timeout) {

        var vm = this;
        vm.idleTime = 3000; // Waiting time until idle sets in

        function checkUserActivity() {
            var timePassed,
                punWindow;
            // Initial starter
            resetTimer();
            // Different events reset the timer
            document.onmousemove = resetTimer;
            document.onkeypress = resetTimer;
            document.onclick = resetTimer;
            document.onscroll = resetTimer;
            document.touchstart = resetTimer;
            // Here comes the logic
            function getRandomPun() {
                $http.get("/pun/random").then(function(resp) {

                    if (punWindow && 'close' in punWindow) {
                        punWindow.close();
                    }
                    // Workaround for multiple ionic popup windows being open
                    // See (atm)bug here: https://github.com/driftyco/ionic/issues/3131
                    $timeout(function(){ 
                        punWindow = $ionicPopup.alert({
                            title: "Shame on you!",
                            template: "" + resp.data.text
                        });
                    }, 0);
                });
            };
            // Reset the interval
            function resetTimer() {
                clearInterval(timePassed);
                timePassed = setInterval(getRandomPun, vm.idleTime);
            }
        }
                
        return {
            checkUserActivity: checkUserActivity
        }
    }

    return randomPunService;
});