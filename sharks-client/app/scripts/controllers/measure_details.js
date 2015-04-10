"use strict";

angular.module("sharksClient")
  .controller("MeasureDetailsCtrl", ["$log", "$window", "$location", "measure", 
                                          function ($log, $window, $location, measure) {
	  this.measure = measure;
	  
	  this.back = function() {
		  $log.info("go back");
		  $window.history.back();
	  };
	  
	  this.goSpecies = function(species) {
		  var hash = "/species/"+species.alphaCode;
		  $log.info("show species routing to "+hash);
		  $location.path(hash);
	  };
  }]);
