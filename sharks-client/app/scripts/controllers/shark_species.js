"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SharkSpeciesCtrl", ["speciesservice", "$log", function (speciesservice, $log) {
	  this.selected = [];
	  this.species = speciesservice.query();
	  
	  this.toggle = function(species) {
		  $log.info("Selected: "+species.scientificName);
		  var index = this.selected.indexOf(species.code);
		  if (index >= 0) this.selected.splice(index,1);
		  else this.selected.push(species.code);
	  };
	  
	  this.isSelected = function(species) {
		  return this.selected.indexOf(species.code)>=0;
	  }
  }]);
