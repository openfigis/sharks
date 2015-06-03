"use strict";

angular.module("sharksClient")
  .controller("CountriesSelectionCtrl", ["routingservice", "pageservice", "countries", "footer", 
                                         function (routingservice, pageservice, countries, footer) {
	  
	  this.groupedCountries = Stream(countries)
	  	.sorted(function(a, b) {
	  		  if (a.code === "EUR") return 1;
	  		  if (b.code === "EUR") return -1;
	  		
		      if (a.name === b.name) return 0;
		      if (a.name < b.name) return -1;
		      return 1;
		   })
	  	.groupBy(function (country) {
	  		if (country.code === "EUR") return "Europe";
	        return country.continent;
	    });
	  
	  pageservice.setTitle("countries");
	  this.footer = footer;

	  this.show = function(country) {
		  routingservice.toSingle("countries", country);
	  };
  }]);
