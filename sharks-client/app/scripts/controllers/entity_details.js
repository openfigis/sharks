"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntityDetailsCtrl", ["$log", "$location", "entity", "measures", "countries",
                                          function ($log, $location, entity, measures, countries) {
	  this.entity = entity;
	  this.measures = measures;
	  this.countries = countries;
	  
	  this.back = function() {
		  $location.path("/entities");
	  };
  }]);
