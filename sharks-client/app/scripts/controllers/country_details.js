"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("CountryDetailsCtrl", ["routingservice", "country", "groupedPoas", "entities",
                                          function (routingservice, country, groupedPoas, entities) {
	  this.country = country;
	  this.groupedPoas = groupedPoas;
	  this.entities = entities;
	  
	  this.back = function() {
		  routingservice.goBack();
	  };
	  
	  this.showPoa = function(poa) {
		routingservice.toSingle("poas", poa);  
	  };
  }]);
