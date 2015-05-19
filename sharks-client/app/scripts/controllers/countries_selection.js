"use strict";

angular.module("sharksClient")
  .controller("CountriesSelectionCtrl", ["routingservice", "pageservice", "countries", "footer", 
                                         function (routingservice, pageservice, countries, footer) {
	  
	  this.groupedCountries = Stream(countries)
	  	.sorted("name")
	  	.groupBy("continent");
	  
	  pageservice.setTitle("countries");
	  this.footer = footer;

	  this.show = function(country) {
		  routingservice.toSingle("countries", country);
	  };
  }]);
