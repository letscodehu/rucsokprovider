define([
  'app'
], function (app) {
  'use strict';
  // definition of routes
  app.config([
	'$stateProvider',
	'$urlRouterProvider',
	function (
			$stateProvider, 
			$urlRouterProvider
			) {
        // url routes/states
        $urlRouterProvider.otherwise('app/tabs');

        $stateProvider
        .state('app', {
            url: '/app',
            templateUrl: 'app/templates/menu.html',
            abstract: true,
            controller : 'menuController'
        }) 
        .state('app.login', {
            url: '/login',
            templateUrl: 'app/templates/login.html',
            controller: 'loginController'
        }) 
        .state('app.add', {
            url: '/add',
            templateUrl: 'app/templates/addRucsok.html',
            controller: 'addRucsokController'
        })
        .state('app.single', {
            url: '/single/{id}',
            templateUrl: 'app/templates/single.html',
            controller: 'singleController'
        })  
        .state('app.about', {
            url: '/about',
            templateUrl: 'app/templates/about.html',
            controller : function() {
            }
        })  
        .state('app.tabs', {
            url : '/tabs',
            views: {
                '' : {
                    templateUrl : 'app/templates/dashboard.html', 
                },
                'fresh-tab@app.tabs' : {
                    templateUrl : 'app/templates/dashboard/tab-fresh.html',
                    controller: 'freshController'
                },
                'hot-tab@app.tabs' : {
                    templateUrl : 'app/templates/dashboard/tab-hot.html',
                    controller: 'hotController'
                },
                'dailyjoke-tab@app.tabs' : {
                    templateUrl : 'app/templates/dashboard/tab-jokes.html',
                    controller: 'jokeController'
                }
            }
        })
        .state('app.profile', {
            url: '/profile',
            onExit: function($rootScope, $ionicTabsDelegate, $ionicHistory) {
                var activeTab = $ionicTabsDelegate.selectedIndex();
                $rootScope.activeProfileTabIndex = activeTab;
            },
            views: {
                '' : {
                    templateUrl : 'app/templates/profile.html',
                    controller: 'profileController',
                },
                'posts-tab@app.profile' : {
                    templateUrl : 'app/templates/profile/tab-posts.html',
                    controller: 'profilePostController'
                },
                'comments-tab@app.profile' : {
                    templateUrl : 'app/templates/profile/tab-comments.html',
                    controller: 'profileCommentController'
                },
                'upvote-tab@app.profile' : {
                    templateUrl : 'app/templates/profile/tab-upvotes.html',
                    controller: 'profileUpvoteController'
                }
            }
        });
	}
  ]);
});
