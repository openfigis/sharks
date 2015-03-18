"use strict";

/**
 * @ngdoc function
 * @name sharksClientApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the sharksClientApp
 */
angular.module("sharksClient")
  .controller("AboutCtrl", function ($scope) {
    $scope.awesomeThings = [
      "HTML5 Boilerplate",
      "AngularJS",
      "Karma"
    ];
  });
