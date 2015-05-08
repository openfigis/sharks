"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("CountriesSelectionCtrl", ["routingservice", "footerservice", "countries", "footer", 
                                         function (routingservice, footerservice, countries, footer) {
	  footerservice.footer = footer;
	  
	  this.countries = countries;

	  this.show = function(country) {
		  routingservice.toSingle("countries", country);
	  };
  }]);
