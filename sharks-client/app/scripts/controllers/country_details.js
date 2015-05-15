"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("CountryDetailsCtrl", ["routingservice", "country",
                                          function (routingservice, country) {
  
	  this.country = country;
	  this.groupedPoas = Stream(country.poas).groupBy(function (poa) {
	      return poa.type;
	  });
	  
	  this.showPoa = function(poa) {
		routingservice.toSingle("poas", poa);  
	  };
	  
	  this.showEntity = function(rfb) {
		routingservice.toSingleById("entities", rfb.acronym);  
	  };
  }]);
