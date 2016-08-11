define([
  'app',
  // Load Controllers here
  'controllers/rucsokController',
  'controllers/addRucsokController',
//  'services/authResolverFactory'
], function (app) {
  'use strict';
  // definition of routes
  app.config([
    '$stateProvider',
    '$urlRouterProvider',
//    'authResolverFactory',
    function (
    		$stateProvider, 
    		$urlRouterProvider
//    		authResolverFactory
    		) {
      // url routes/states
      $urlRouterProvider.otherwise('dashboard');

      $stateProvider
        // app states
      	.state('login', {
          url: '/login',
          templateUrl: 'app/templates/login.html',
          controller: 'loginController'
        }) 
        .state('dashboard', {
          url: '/dashboard',
          templateUrl: 'app/templates/dashboard.html',
          controller: 'dashboardController'
        }) 
        .state('single', {
            url: '/single/{id}',
            templateUrl: 'app/templates/single.html',
            controller: 'singleController'
        });
    }
  ]);
});
