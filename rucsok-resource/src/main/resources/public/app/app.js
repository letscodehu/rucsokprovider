// The main app definition
// --> where you should load other module depdendencies
define([
  'ionic', 
  "services/services",
  "controllers/controllers",
  "directives/directives",
  "filters/filters",
  'ionic-gallery',
  'ion-floating-menu',
  'angular-http-auth',
  'pascalprecht.translate',
  'spring-token-interceptor'
], function () {
  'use strict';

  // the app with its used plugins
  var app = angular.module('app', ['ionic', "services", "controllers", "directives", 'filters', 'ion-gallery',  'http-auth-interceptor', 'pascalprecht.translate', 'ion-floating-menu' 
  ])
  // Setup languages and variables
  .config([ '$translateProvider', function($translateProvider) {
    $translateProvider.translations('en', {
      "LOGIN": "Login",
      "USERNAME": "Username",
      "PASSWORD": "Password"
    });

    $translateProvider.translations('hu', {
      "LOGIN": "Bejelentkezés",
      "USERNAME": "Felhasználó",
      "PASSWORD": "Jelszó"
    });
    $translateProvider.registerAvailableLanguageKeys(['en', 'hu'], {
      'en-*': 'en',
      'hu-*': 'hu'
    });
    $translateProvider.preferredLanguage('en');
  }])
  // Run idle checker
  .run(function(randomPunService) {
    randomPunService.checkUserActivity();
  });

  // return the app so you can require it in other components
  return app;
});
