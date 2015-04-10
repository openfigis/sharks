"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("CountriesSelectionCtrl", ["routingservice", "countries", 
                                         function (routingservice, countries) {
	  
	  this.countries = countries;

	  this.show = function(country) {
		  routingservice.toSingle("countries", country);
	  };
  }]);
