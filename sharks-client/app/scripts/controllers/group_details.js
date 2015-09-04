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
	  pageservice.setDescription("Measures for species group "+group.name);
	  
	  this.group = group;
	  this.ems = Stream(group.measures)
	  .sort(function(a, b) {
		      if (a.year === b.year) return 0;
		      if (a.year > b.year) return -1;
		      return 1;
		   })
	  .groupBy(function (measure) {
	      return measure.entityAcronym;
	  });
	  
	  this.showEntity = function(acronym) {
		  routingservice.toSingleById("entities",acronym);
	  };
	  
	  this.showSpecies = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.imageUrl = imagesservice.groupMediumImageUrl;
	  this.missingImageUrl = imagesservice.groupMissingMediumImageUrl;
  }]);
