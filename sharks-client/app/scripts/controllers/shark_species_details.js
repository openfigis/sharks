"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SharkSpeciesDetailsCtrl", ["measuresresource", "$log", "$location", "$routeParams", 
                                          function (measuresservice, $log, $location, $routeParams) {
	  this.ids = $routeParams.ids.split(",");
	  $log.info("ids "+this.ids);
	  this.ems = measuresservice.groupByEntity({species:this.ids});
	  
	  this.back = function() {
		  $location.path("/species");
	  }
  }]);
