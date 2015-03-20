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
	  this.selected = speciesservice.selected;
	  this.toggle = speciesservice.toggleSelection;
	  this.isSelected = speciesservice.isSelected;
	  
	  this.species = speciesservice.list();

	  this.showSpecies = function() {
		  var hash = "/species/"+this.selected.join(",");
		  $log.info("showSpecies: "+this.selected+ " routing to "+hash);
		  $location.path(hash);
	  }
  }]);
