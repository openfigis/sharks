"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SharkSpeciesDetailsCtrl", ["measuresresource", "speciesservice", "$log", "$location", "$routeParams", 
                                          function (measuresservice, speciesservice, $log, $location, $routeParams) {
	  this.ids = $routeParams.ids.split(",").map(function(item) {
		    return parseInt(item, 10);
	  });
	  speciesservice.selected = this.ids;
	 
	  var self = this;
	  speciesservice.getAll(this.ids).then(function (species) {
		  self.species = species;
	  });
	  
	  this.ems = measuresservice.groupByEntity({species:this.ids});
	  
	  this.back = function() {
		  $location.path("/species");
	  }
  }]);
