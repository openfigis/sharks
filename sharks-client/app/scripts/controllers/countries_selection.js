"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("CountriesSelectionCtrl", ["routingservice", "groupedCountries", "footer", 
                                         function (routingservice, groupedCountries, footer) {
	  
	  this.groupedCountries = groupedCountries;
	  
	  this.footer = footer;

	  this.show = function(country) {
		  routingservice.toSingle("countries", country);
	  };
  }]);
