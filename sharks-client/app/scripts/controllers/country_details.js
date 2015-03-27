"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("CountryDetailsCtrl", ["$log", "$location", "country", "groupedPoas", 
                                          function ($log, $location, country, groupedPoas) {
	  this.country = country;
	  this.groupedPoas = groupedPoas;
	  
	  this.back = function() {
		  $location.path("/countries");
	  };
  }]);
