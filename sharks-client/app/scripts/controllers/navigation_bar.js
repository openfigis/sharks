"use strict";

angular.module("sharksClient")
  .controller("NavigationBarCtrl", ["$location", "paths", 
                                         function ($location, paths) {
	  this.paths = paths;

	  this.isActive = function(path) { 
	  	return $location.path().startsWith(path.all) || $location.path().startsWith(path.singlePath);
	  };

  }]);
