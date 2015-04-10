"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesSelectionCtrl", ["$log", "$location", "species", function ($log, $location, species) {
	  this.species = species;
	  this.show = function(species) {
		  var hash = "/species/"+species.alphaCode;
		  $log.info("show species routing to "+hash);
		  $location.path(hash);
	  };
  }]);
