"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("GroupDetailsCtrl", ["routingservice", "imagesservice", "group", "ems",  
                                          function (routingservice, imagesservice, group, ems) {
	  
	  this.group = group;
	  this.ems = ems;
	  
	  this.imageUrl = imagesservice.groupMediumImageUrl;
	  this.missingImageUrl = imagesservice.groupMissingMediumImageUrl;
  }]);
