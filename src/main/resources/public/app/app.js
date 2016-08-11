// The main app definition
// --> where you should load other module depdendencies
define([
  'ionic', 
  "services/services",
  "controllers/controllers",
  "directives/directives",
  'ionic-gallery',
  'angular-http-auth',
], function () {
  'use strict';

  // the app with its used plugins
  var app = angular.module('app', [
    'ionic', "services", "controllers", "directives", 'ion-gallery', 'http-auth-interceptor'
  ]);
  // return the app so you can require it in other components
  return app;
});
