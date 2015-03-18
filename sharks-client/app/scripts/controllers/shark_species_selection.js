"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SharkSpeciesSelectionCtrl", ["speciesservice", "$log", "$location", function (speciesservice, $log, $location) {
	  this.selected = [];
	  this.species = speciesservice.query();
	  
	  this.toggle = function(species) {
		  $log.info("Selected: "+species.scientificName);
		  var index = this.selected.indexOf(species.code);
		  this.selected.length = 0;  
		  if (index < 0) this.selected.push(species.code);
	  };
	  
	  this.isSelected = function(species) {
		  return this.selected.indexOf(species.code)>=0;
	  }
	  
	  this.showSpecies = function() {
		  $log.info("showSpecies: "+this.selected[0]);
		  $location.path("#/species/"+this.selected[0]);
	  }
  }]);
