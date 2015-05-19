"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntitiesSelectionCtrl", ["routingservice", "entities", "footer", 
                                         function (routingservice, entities, footer) {
	  
	  this.entities = Stream(entities)
	  		.filter(function (entity) {
			  return entity.acronym !== "CITES" && entity.acronym !== "CMS";
	  		})
	  		.toArray();
	  
	  this.footer = footer;

	  this.show = function(entity) {
		  routingservice.toSingle("entities", entity);
	  };
	  
	  this.showCMS = function() {
		  routingservice.toSingleById("entities", "CMS");
	  };
	  
	  this.showCITES = function() {
		  routingservice.toSingleById("entities", "CITES");
	  };
  }]);
