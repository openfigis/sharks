"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("CountriesSelectionCtrl", ["routingservice", "footerservice", "groupedCountries", "footer", 
                                         function (routingservice, footerservice, groupedCountries, footer) {
	  footerservice.footer = footer;
	  
	  this.groupedCountries = groupedCountries;

	  this.show = function(country) {
		  routingservice.toSingle("countries", country);
	  };
  }]);
