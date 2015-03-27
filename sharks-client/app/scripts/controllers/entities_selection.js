"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("EntitiesSelectionCtrl", ["entitiesservice", "pathservice", "$log", "$location", "entities", 
                                         function (entitiesservice, pathservice, $log, $location, entities) {
	  this.selected = entitiesservice.selected;
	  this.toggle = entitiesservice.toggleSelection;
	  this.isSelected = entitiesservice.isSelected;
	  
	  this.entities = entities;

	  this.showSelected = function() {
		  var hash = "/entities/"+this.selected;
		  $log.info("showSelected: "+this.selected+ " routing to "+hash);
		  $location.path(hash);
	  };
  }]);
