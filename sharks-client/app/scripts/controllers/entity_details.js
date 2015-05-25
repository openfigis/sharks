"use strict";

angular.module("sharksClient")
  .controller("EntityDetailsCtrl", ["routingservice", "pageservice", "factsheets", "mapViewer", "rfb", "entity",
                                          function (routingservice, pageservice, factsheets, mapViewer, rfb, entity) {
	  
	  this.entity = entity;
	  
	  this.hasCompentenceAreaMap = entity.acronym !== "CITES" && entity.acronym !== "CMS"; 
	  
	  this.factsheetsUrl = entity.acronym !== null? factsheets.rfbBaseUrl + entity.acronym : null;
	  this.logoUrl = entity.imageId !== null? rfb.logoBaseUrl + entity.imageId : null;
	  
	  pageservice.setTitle(entity.acronym);
	  
	  this.showCountry = function(country) {
		routingservice.toSingle("countries", country);  
	  };
	  
	  this.mapUrl = mapViewer.rfbBaseUrl;	  
	  
	  if (this.hasCompentenceAreaMap) {
		  FigisMap.draw( { 
			  target:"areaMap", 
			  rfb: entity.acronym, 
			  context: "FI-Org", 
			  legend:"OrgMapLegend",
			  mapSize:"S" });
	  }
		
  }]);
