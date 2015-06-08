"use strict";

angular.module("sharksClient")
  .controller("CountryDetailsCtrl", ["routingservice", "pageservice", "imagesservice", "countryprofiles", "faolex", "country",
                                          function (routingservice, pageservice, imagesservice, countryprofiles, faolex, country) {
  
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
	  
	  this.others = Stream(country.others)
	  		.sort(function(a, b) {
			    if (a.year === b.year) return 0;
			    if (a.year > b.year) return -1;
			    return 1;
			 })
			 .toArray();
	  
	  this.docs = Stream(country.faoLexDocuments)
		.sort(function(a, b) {
		    if (a.year === b.year) return 0;
		    if (a.year > b.year) return -1;
		    return 1;
		 })
		 .toArray();
	  
	  this.flagUrl = imagesservice.countryFlagUrl(country);
	  this.noFlagUrl = imagesservice.missingFlagUrl;
	  this.profileUrl = countryprofiles.profileBaseUrl+country.code+"/en";
	  this.faoLexUrl = faolex.baseUrl + country.code;
	  
	  pageservice.setTitle(country.name);
	  
	  this.showPoa = function(poa) {
		routingservice.toSingle("poas", poa);  
	  };
	  
	  this.showEntity = function(rfb) {
		routingservice.toSingleById("entities", rfb.acronym);  
	  };

  }]);
