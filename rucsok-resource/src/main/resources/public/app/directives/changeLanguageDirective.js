define([], function() {
    
    function changeLanguage() {
        var changeLanguageController = function($translate) {
            var colSizes = [10, 20, 25, 33, 50,67, 75, 80, 90];

            var chooseColumnSize = function(value) {
                var i,
                    colSizesLength = colSizes.length,
                    max = colSizes[0];
                for (i = 0; i < colSizesLength; i++) {
                    if (colSizes[i] <= value ) {
                        max = colSizes[i];
                    } else {
                        return max;
                    }
                }
            };

            this.languages = null;

            this.currentLanguage = $translate.use();

            if ($translate.isReady()) {
//                this.languages = $translate.getAvailableLanguageKeys();
                this.languages = ['en'];
            }

            this.changeLanguage = function(langKey) {
                $translate.use(langKey);
            };

            this.isCurrentLanguage = function(langKey) {
                return $translate.use() === langKey;
            } 

            this.className = 'col-' + chooseColumnSize(parseInt(100/parseInt(this.languages.length)));
        }

        return {
            "restrict" : 'E',
            "scope": {},
            "templateUrl" : 'app/templates/changeLanguage.html',
            "controller": changeLanguageController,
            "controllerAs": 'vm'
        }
    }
    
    return changeLanguage;
});