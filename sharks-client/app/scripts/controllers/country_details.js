"use strict";

angular.module("sharksClient")
  .controller("CountryDetailsCtrl", ["routingservice", "pageservice", "country",
                                          function (routingservice, pageservice, country) {
  
	  this.country = country;
	  this.groupedPoas = Stream(country.poas).groupBy(function (poa) {
	      return poa.type;
	  });
	  
	  pageservice.setTitle(country.name);
	  
	  this.showPoa = function(poa) {
		routingservice.toSingle("poas", poa);  
	  };
	  
	  this.showEntity = function(rfb) {
		routingservice.toSingleById("entities", rfb.acronym);  
	  };
  }]);
