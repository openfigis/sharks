"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("GroupDetailsCtrl", ["routingservice", "imagesservice", "group",   
                                          function (routingservice, imagesservice, group) {
	  
	  this.group = group;
	  this.ems = Stream(group.measures)
	  .filter(function (measure) {
		  return measure.entityAcronym !== "CITES" && measure.entityAcronym !== "CMS";
	  })
	  .groupBy(function (measure) {
	      return measure.entityAcronym;
	  });
	  
	  this.showCitesLink = Stream(group.measures).anyMatch({entityAcronym:"CITES"});
	  this.showCmsLink = Stream(group.measures).anyMatch({entityAcronym:"CMS"});
	  
	  this.showEntity = function(acronym) {
		  routingservice.toSingleById("entities",acronym);
	  };
	  
	  this.showSpecies = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.imageUrl = imagesservice.groupMediumImageUrl;
	  this.missingImageUrl = imagesservice.groupMissingMediumImageUrl;
  }]);
