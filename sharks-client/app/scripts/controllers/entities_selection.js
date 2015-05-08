"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntitiesSelectionCtrl", ["routingservice", "footerservice", "entities", "footer", 
                                         function (routingservice, footerservice, entities, footer) {
	  footerservice.footer = footer;
	  
	  this.entities = entities;

	  this.show = function(entity) {
		  routingservice.toSingle("entities", entity);
	  };
  }]);
