"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesDetailsCtrl", ["routingservice", "imagesservice", "factsheets", "species",  
                                          function (routingservice, imagesservice, factsheets, species) {
	  
	  this.species = species;
	  this.ems = Stream(species.measures)
		  .filter(function (measure) {
			  return measure.entityAcronym !== "CITES" && measure.entityAcronym !== "CMS";
		  })
		  .groupBy(function (measure) {
		      return measure.entityAcronym;
		  });
	  
	  this.showCitesLink = Stream(species.measures).anyMatch({entityAcronym:"CITES"});
	  this.showCmsLink = Stream(species.measures).anyMatch({entityAcronym:"CMS"});
	  
	  this.factsheetsUrl = species.figisId !== null? factsheets.baseUrl + species.figisId : null;
	  
	  this.showEntity = function(acronym) {
		  routingservice.toSingleById("entities",acronym);
	  };
	  
	  this.imageUrl = imagesservice.speciesMediumImageUrl;
	  this.missingImageUrl = imagesservice.speciesMissingMediumImageUrl;
  }]);
