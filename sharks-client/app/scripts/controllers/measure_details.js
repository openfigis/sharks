"use strict";

angular.module("sharksClient")
  .controller("MeasureDetailsCtrl", ["routingservice", "measure", function (routingservice, measure) {
	  this.measure = measure;
	  
	  this.back = function() {
		  routingservice.goBack();
	  };
	  
	  this.showSpecies = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.showEntity = function(entity) {
		  routingservice.toSingle("entities", entity);
	  };
  }]);
