"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesDetailsCtrl", ["routingservice", "ems", "species", 
                                          function (routingservice, ems, species) {
	  this.species = species;
	  this.ems = ems;
	  
	  this.back = function() {
		  routingservice.goBack();
	  };
	  
	  this.goMeasure = function(measure) {
		  routingservice.toSingle("measures",measure);
	  };
  }]);
