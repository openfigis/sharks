"use strict";

angular.module("sharksClient")
  .controller("CountriesSelectionCtrl", ["routingservice", "countries", "footer", 
                                         function (routingservice, countries, footer) {
	  
	  this.groupedCountries = Stream(countries)
	  	.sorted("name")
	  	.groupBy("continent");
	  
	  this.footer = footer;

	  this.show = function(country) {
		  routingservice.toSingle("countries", country);
	  };
  }]);
