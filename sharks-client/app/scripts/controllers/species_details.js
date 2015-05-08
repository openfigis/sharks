"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesDetailsCtrl", ["routingservice", "imagesservice", "footerservice", "ems", "species", "footer", 
                                          function (routingservice, imagesservice, footerservice, ems, species, footer) {
	  footerservice.footer = footer;
	  
	  this.species = species;
	  this.ems = ems;
	  
	  this.back = function() {
		  routingservice.goBack();
	  };
	  
	  this.goMeasure = function(measure) {
		  routingservice.toSingle("measures",measure);
	  };
	  
	  this.imageUrl = imagesservice.speciesMediumImageUrl;
	  this.missingImageUrl = imagesservice.speciesMissingMediumImageUrl;
  }]);
