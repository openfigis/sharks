"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("GroupDetailsCtrl", ["routingservice", "imagesservice", "pageservice", "group",   
                                          function (routingservice, imagesservice, pageservice, group) {
	  
	  pageservice.setTitle(group.name);
	  
	  this.group = group;
	  this.ems = Stream(group.measures)
	  .sort(function(a, b) {
		      if (a.year === b.year) return 0;
		      if (a.year > b.year) return -1;
		      return 1;
		   })
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
