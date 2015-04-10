"use strict";

angular.module("sharksClient")
  .controller("PoaDetailsCtrl", ["routingservice", "poa", function (routingservice, poa) {
	  this.poa = poa;
	  
	  this.back = function() {
		  routingservice.goBack();
	  };
	  
	  this.showCountry = function(country) {
		  routingservice.toSingle("countries", country);
	  };
	  
	  this.showEntity = function(entity) {
		  routingservice.toSingle("entities", entity);
	  };
  }]);
