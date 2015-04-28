"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesSelectionCtrl", ["routingservice", "imagesservice", "species", function (routingservice, imagesservice, species) {
	  this.species = species;
	  this.show = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.imageUrl = imagesservice.speciesSmallImageUrl;
	  this.missingUrl = imagesservice.speciesMissingSmallImageUrl;
  }]);
