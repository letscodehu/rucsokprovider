define(["ionic"],
        function() {
    
    removeNumSign.$inject = []
    
    function removeNumSign() {
        
         return function (number) {
                return window.Math.abs(number);
           };
        
    }
    
    return removeNumSign;
    
});