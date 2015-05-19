"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesDetailsCtrl", ["routingservice", "imagesservice", "pageservice", 
                                     "factsheets", "mapViewer", "species",  
                                          function (routingservice, imagesservice, pageservice, 
                                        		  factsheets, mapViewer, species) {
	  
	  pageservice.setTitle(species.officialNames.en);
	  
	  this.species = species;
	  this.ems = Stream(species.measures)
		  .filter(function (measure) {
			  return measure.entityAcronym !== "CITES" && measure.entityAcronym !== "CMS";
		  })
		  .groupBy(function (measure) {
		      return measure.entityAcronym;
		  });
	  
	  this.showCitesLink = Stream(species.measures).anyMatch({entityAcronym:"CITES"});
	  this.showCmsLink = Stream(species.measures).anyMatch({entityAcronym:"CMS"});
	  
	  this.factsheetsUrl = species.figisId !== null? factsheets.speciesBaseUrl + species.figisId : null;
	  
	  this.showEntity = function(acronym) {
		  routingservice.toSingleById("entities", acronym);
	  };
	  
	  this.imageUrl = imagesservice.speciesMediumImageUrl;
	  this.missingImageUrl = imagesservice.speciesMissingMediumImageUrl;
	  
	  this.mapUrl = mapViewer.speciesBaseUrl+"?species="+species.alphaCode+"-m&prj=4326";
	  
      FigisMap.draw({ 
          context:"FI-species", 
          target:"distributionMap", 
          mapSize:"L", 
          distribution:[{ layer:"fifao:SPECIES_DIST", 
            filter:"ALPHACODE='"+species.alphaCode+"'", 
            title:"Abramis brama", 
            autoZoom:true }], 
            legend:"mapSpeciesLegend", 
            staticLabels:{ "MAIN LAYERS" : "Species distribution" } });
  }]);
