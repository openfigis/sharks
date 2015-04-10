"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesDetailsCtrl", ["$log", "$location", "ems", "species", 
                                          function ($log, $location, ems, species) {
	  this.species = species;
	  this.ems = ems;
	  
	  this.back = function() {
		  $location.path("/species");
	  };
	  
	  this.goMeasure = function(measure) {
		  $location.path("measures/"+measure.id);
	  };
  }]);
