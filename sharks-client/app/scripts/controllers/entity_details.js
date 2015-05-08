"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntityDetailsCtrl", ["routingservice", "footerservice", "entity", "measures", "countries", "footer",
                                          function (routingservice, footerservice, entity, measures, countries, footer) {
	  footerservice.footer = footer;
	  
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
