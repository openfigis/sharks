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
	  this.ems = Stream(group.measures).groupBy(function (measure) {
	      return measure.entityAcronym;
	  });
	  
	  this.imageUrl = imagesservice.groupMediumImageUrl;
	  this.missingImageUrl = imagesservice.groupMissingMediumImageUrl;
  }]);
