"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntityDetailsCtrl", ["routingservice", "factsheets", "mapViewer", "rfb", "entity",
                                          function (routingservice, factsheets, mapViewer, rfb, entity) {
	  
	  this.entity = entity;
	  
	  this.factsheetsUrl = entity.acronym !== null? factsheets.rfbBaseUrl + entity.acronym : null;
	  this.logoUrl = entity.imageId !== null? rfb.logoBaseUrl + entity.imageId : null;
	  
	  this.showCountry = function(country) {
		routingservice.toSingle("countries", country);  
	  };
	  
	  this.mapUrl = mapViewer.rfbBaseUrl;	  
	  
	  FigisMap.draw( { 
		  target:"areaMap", 
		  rfb: entity.acronym, 
		  context: "FI-Org", 
		  legend:"OrgMapLegend",
		  mapSize:"S" });
		
  }]);
