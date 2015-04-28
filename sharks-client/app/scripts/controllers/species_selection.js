"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesSelectionCtrl", ["routingservice", "species", function (routingservice, species) {
	  this.species = species;
	  this.show = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.imageUrl = function(species) {
		  var sn = species.scientificName.toLowerCase().replace(" ", "_");
		  return "images/species/"+sn+"-drawing-small.gif";  
	  };
  }]);
