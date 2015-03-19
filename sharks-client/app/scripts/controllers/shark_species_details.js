"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SharkSpeciesDetailsCtrl", ["speciesservice", "$log", "$location", "$routeParams", 
                                          function (speciesservice, $log, $location, $routeParams) {
	  this.ids = $routeParams.ids.split(",");
	  this.sspMeasures = [];
  }]);
