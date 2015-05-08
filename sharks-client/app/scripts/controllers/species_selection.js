"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesSelectionCtrl", ["routingservice", "imagesservice", "footerservice", "species", "groups", "footer",
                                       function (routingservice, imagesservice, footerservice, species, groups, footer) {
	  footerservice.footer = footer;
	  
	  this.species = species;
	  this.groups = groups;
	  
	  this.show = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.imageUrl = imagesservice.speciesSmallImageUrl;
	  this.missingImageUrl = imagesservice.speciesMissingSmallImageUrl;
  }]);
