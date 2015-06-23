"use strict";

angular.module("sharksClient")
  .controller("EntityDetailsCtrl", ["routingservice", "pageservice", "entityTypes", "mapViewer", "entity",
                                          function (routingservice, pageservice, entityTypes, mapViewer, entity) {
	  
	  this.entity = entity;
	  
	  this.ems = Stream(entity.measures)
	  		.sort(function(a, b) {
		      if (a.year === b.year) return 0;
		      if (a.year > b.year) return -1;
		      return 1;
		   })
		   .toArray();
	  
	  this.others = Stream(entity.others)
	  	  		.sort(function(a, b) {
		      if (a.year === b.year) return 0;
		      if (a.year > b.year) return -1;
		      return 1;
		   })
		   .toArray();
	  
	  this.hasCompentenceAreaMap = entity.type === entityTypes.rfmo; 
	  
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
