"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntityDetailsCtrl", ["routingservice", "entity", "measures", "countries",
                                          function (routingservice, entity, measures, countries) {
	  this.entity = entity;
	  this.measures = measures;
	  this.countries = countries;
	  
	  this.back = function() {
		routingservice.goBack();
	  };
	  
	  this.showMeasure = function(measure) {
		routingservice.toSingle("measures", measure);  
	  };
  }]);
