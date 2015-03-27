"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("CountriesSelectionCtrl", ["countriesservice", "pathservice", "$log", "$location", "countries", 
                                         function (countriesservice, pathservice, $log, $location, countries) {
	  this.selected = countriesservice.selected;
	  this.toggle = countriesservice.toggleSelection;
	  this.isSelected = countriesservice.isSelected;
	  
	  this.countries = countries;

	  this.showSelected = function() {
		  var hash = "/countries/"+this.selected;
		  $log.info("showSelected: "+this.selected+ " routing to "+hash);
		  $location.path(hash);
	  };
  }]);
