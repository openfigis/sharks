"use strict";

angular.module("sharksClient")
  .controller("NavigationBarCtrl", ["$location", 
                                         function ($location) {
	  this.isActive = function (viewLocation) { 
	  	return viewLocation === $location.hash();
	  };

  }]);
