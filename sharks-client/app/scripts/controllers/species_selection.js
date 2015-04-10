"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesSelectionCtrl", ["routingservice", "species", function (routingservice, species) {
	  this.species = species;
	  this.show = function(species) {
		  routingservice.toSingle("species", species);
	  };
  }]);
