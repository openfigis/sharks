'use strict';

/**
 * @ngdoc overview
 * @name sharksClientApp
 * @description
 * # sharksClientApp
 *
 * Main module of the application.
 */
angular
  .module('sharksClientApp', [
    'ngResource',
    'ngRoute'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
