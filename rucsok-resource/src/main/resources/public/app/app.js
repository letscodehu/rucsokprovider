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
  'spring-token-interceptor'
], function () {
  'use strict';

  // the app with its used plugins
  var app = angular.module('app', [
    'ionic', "services", "controllers", "directives", 'filters', 'ion-gallery',  'http-auth-interceptor', 'ion-floating-menu'
  ]);
  // return the app so you can require it in other components
  return app;
});
