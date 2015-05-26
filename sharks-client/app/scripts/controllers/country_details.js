"use strict";

angular.module("sharksClient")
  .controller("CountryDetailsCtrl", ["routingservice", "pageservice", "countryprofiles", "country",
                                          function (routingservice, pageservice, countryprofiles, country) {
  
	  this.country = country;
	  this.groupedPoas = Stream(country.poas)
	  		.sort(function(a, b) {
	  			if (a.poAYear === b.poAYear) return 0;
	  			if (a.poAYear > b.poAYear) return -1;
	  			return 1;
	  		})
	  		.groupBy(function (poa) {
	  			return poa.type;
	  });
	  
	  this.flagBaseUrl = countryprofiles.flagBaseUrl;
	  
	  pageservice.setTitle(country.name);
	  
	  this.showPoa = function(poa) {
		routingservice.toSingle("poas", poa);  
	  };
	  
	  this.showEntity = function(rfb) {
		routingservice.toSingleById("entities", rfb.acronym);  
	  };
	  
	  this.submitFaoLexForm = function() {
		  document.getElementById("faolexForm").submit();
	  };
  }]);
