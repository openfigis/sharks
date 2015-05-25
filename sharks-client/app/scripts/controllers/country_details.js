"use strict";

angular.module("sharksClient")
  .controller("CountryDetailsCtrl", ["routingservice", "pageservice", "country",
                                          function (routingservice, pageservice, country) {
  
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
