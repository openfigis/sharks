"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesSelectionCtrl", ["routingservice", "imagesservice", "species", "groups",
                                       function (routingservice, imagesservice, species, groups) {
	  this.species = species;
	  this.groups = groups;
	  
	  this.show = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.imageUrl = imagesservice.speciesSmallImageUrl;
	  this.missingImageUrl = imagesservice.speciesMissingSmallImageUrl;
  }]);
