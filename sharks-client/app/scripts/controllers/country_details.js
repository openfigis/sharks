"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("CountryDetailsCtrl", ["$log", "$location", "country", "groupedPoas", "entities",
                                          function ($log, $location, country, groupedPoas, entities) {
	  this.country = country;
	  this.groupedPoas = groupedPoas;
	  this.entities = entities;
	  
	  this.back = function() {
		  $location.path("/countries");
	  };
  }]);
