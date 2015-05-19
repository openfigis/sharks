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
	  
	  this.entitiesInMap = []; 
	  
	  this.entities = Stream(entities)
	  		.sorted("acronym")
	  		.filter(function (entity) {
			  return entity.acronym !== "CITES" && entity.acronym !== "CMS";
	  		})
	  		.toArray();
	  
	  this.footer = footer;

	  this.show = function(entity) {
		  routingservice.toSingle("entities", entity);
	  };
	  
	  this.toggleInMap = function(entity) {
		  
		  var index = this.entitiesInMap.indexOf(entity.acronym);
		  if (index >= 0) this.entitiesInMap.splice(index, 1);
		  else this.entitiesInMap.push(entity.acronym);

	  };
	  
	  this.isInMap = function(entity) {
		  return this.entitiesInMap.indexOf(entity.acronym) >= 0 ;
	  };
	  
	  this.showCMS = function() {
		  routingservice.toSingleById("entities", "CMS");
	  };
	  
	  this.showCITES = function() {
		  routingservice.toSingleById("entities", "CITES");
	  };
  }]);
