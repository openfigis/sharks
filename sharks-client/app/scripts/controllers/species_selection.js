"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesSelectionCtrl", ["speciesservice", "pathservice", "$log", "$location", "species", function (speciesservice, pathservice, $log, $location, species) {
	  this.selected = speciesservice.selected;
	  this.toggle = speciesservice.toggleSelection;
	  this.isSelected = speciesservice.isSelected;
	  
	  this.species = species;

	  this.showSpecies = function() {
		  var hash = "/species/"+pathservice.encode(this.selected);
		  $log.info("showSpecies: "+this.selected+ " routing to "+hash);
		  $location.path(hash);
	  };
  }]);
