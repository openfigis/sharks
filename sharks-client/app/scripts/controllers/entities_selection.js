"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntitiesSelectionCtrl", ["routingservice", "entities", 
                                         function (routingservice, entities) {
	  
	  this.entities = entities;

	  this.show = function(entity) {
		  routingservice.toSingle("entities", entity);
	  };
  }]);
