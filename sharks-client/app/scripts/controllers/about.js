"use strict";

/**
 * @ngdoc function
 * @name sharksClientApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the sharksClientApp
 */
angular.module("sharksClientApp")
  .controller("AboutCtrl", function ($scope) {
    $scope.awesomeThings = [
      "HTML5 Boilerplate",
      "AngularJS",
      "Karma"
    ];
  });
