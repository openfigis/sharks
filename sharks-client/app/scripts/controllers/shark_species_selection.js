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
		  if (index < 0) this.selected.push(species.code);
		  else this.selected.splice(index, 1);
	  };
	  
	  this.isSelected = function(species) {
		  return this.selected.indexOf(species.code)>=0;
	  }
	  
	  this.showSpecies = function() {
		  var hash = "/species/"+this.selected.join(",");
		  $log.info("showSpecies: "+this.selected+ " routing to "+hash);
		  $location.path(hash);
	  }
  }]);
