define([ 'jquery' ], function($) {

    randomPunService.$inject = [ '$http', '$q', '$ionicPopup' ];

    function randomPunService($http, $q, $ionicPopup) {

        var vm = this;
        vm.idleTime = 120000; // Waiting time until idle sets in

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
                        punWindow.close(this);
                    }
                    punWindow = $ionicPopup.alert({
                        title: "Shame on you!",
                        template: "" + resp.data.text
                    });
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