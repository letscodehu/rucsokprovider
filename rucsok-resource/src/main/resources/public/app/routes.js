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
      $urlRouterProvider.otherwise('app/dashboard');

      $stateProvider
      .state('app', {
          url: '/app',
          templateUrl: 'app/templates/menu.html',
          abstract: true,
          controller : 'menuController'
        }) 
        // app states
      	.state('app.login', {
          url: '/login',
          templateUrl: 'app/templates/login.html',
          controller: 'loginController'
        }) 
         // app states
      	.state('app.add', {
          url: '/add',
          templateUrl: 'app/templates/addRucsok.html',
          controller: 'addRucsokController'
        }) 
        .state('app.dashboard', {
          url: '/dashboard',
    		  templateUrl : 'app/templates/dashboard.html',
    		  controller: 'dashboardController'
        }) 
        .state('app.single', {
            url: '/single/{id}',
            templateUrl: 'app/templates/single.html',
            controller: 'singleController'
        });
    }
  ]);
});
