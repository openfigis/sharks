"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntityDetailsCtrl", ["routingservice", "factsheets", "rfb", "entity",
                                          function (routingservice, factsheets, rfb, entity) {
	  
	  this.entity = entity;
	  
	  this.factsheetsUrl = entity.acronym !== null? factsheets.rfbBaseUrl + entity.acronym : null;
	  this.logoUrl = entity.imageId !== null? rfb.logoBaseUrl + entity.imageId : null;
	  
	  this.showCountry = function(country) {
		routingservice.toSingle("countries", country);  
	  };
  }]);
