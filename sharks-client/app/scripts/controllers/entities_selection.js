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
	  
	  this.entities = entities;
	  
	  this.footer = footer;

	  this.show = function(entity) {
		  routingservice.toSingle("entities", entity);
	  };
  }]);
