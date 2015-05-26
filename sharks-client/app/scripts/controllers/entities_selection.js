"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntitiesSelectionCtrl", ["routingservice", "pageservice", "entityTypes", "entities", "footer", 
                                         function (routingservice, pageservice, entityTypes, entities, footer) {
	  
	  this.entitiesInMap = []; 
	  
	  this.rfmos = Stream(entities)
	  		.filter(function (entity) {
			  return entity.type === entityTypes.rfmo;
	  		})
	  		.sorted("acronym")
	  		.toArray();
	  
	  this.institutions = Stream(entities)
			.filter(function (entity) {
			  return entity.type === entityTypes.institution;
			})
			.sorted("acronym")
			.toArray();
	  
	  pageservice.setTitle("institutions");
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

  }]);
