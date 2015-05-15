"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntityDetailsCtrl", ["routingservice", "entity",
                                          function (routingservice, entity) {
	  
	  this.entity = entity;
	  /* TODO
	  this.measures = Stream(entity.measures).groupBy(function (measure) {
	      return measure.entityAcronym;
	  });*/
	  
	  this.showMeasure = function(measure) {
		routingservice.toSingle("measures", measure);  
	  };
  }]);
