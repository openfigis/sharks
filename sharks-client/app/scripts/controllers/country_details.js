"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("CountryDetailsCtrl", ["routingservice", "footerservice", "country", "groupedPoas", "entities", "footer",
                                          function (routingservice, footerservice, country, groupedPoas, entities, footer) {
	  footerservice.footer = footer;
	  
	  this.country = country;
	  this.groupedPoas = groupedPoas;
	  this.entities = entities;
	  
	  this.back = function() {
		  routingservice.goBack();
	  };
	  
	  this.showPoa = function(poa) {
		routingservice.toSingle("poas", poa);  
	  };
	  
	  this.showEntity = function(acronym) {
		routingservice.toSingleById("entities", acronym);  
	  };
  }]);
