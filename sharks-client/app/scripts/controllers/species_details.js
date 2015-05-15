"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesDetailsCtrl", ["routingservice", "imagesservice", "species",  
                                          function (routingservice, imagesservice, species) {
	  
	  this.species = species;
	  this.ems = Stream(species.measures).groupBy(function (measure) {
	      return measure.entityAcronym;
	  });
	  
	  this.goMeasure = function(measure) {
		  routingservice.toSingle("measures",measure);
	  };
	  
	  this.imageUrl = imagesservice.speciesMediumImageUrl;
	  this.missingImageUrl = imagesservice.speciesMissingMediumImageUrl;
  }]);
