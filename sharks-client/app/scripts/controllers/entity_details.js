"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntityDetailsCtrl", ["$log", "$location", "entity", "measures", 
                                          function ($log, $location, entity, measures) {
	  this.entity = entity;
	  this.measures = measures;
	  
	  this.back = function() {
		  $location.path("/entities");
	  };
  }]);
