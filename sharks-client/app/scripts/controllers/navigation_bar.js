"use strict";

angular.module("sharksClient")
  .controller("NavigationBarCtrl", ["$location", "paths", 
                                         function ($location, paths) {
	  this.paths = paths;

	  this.href = function(path) { 
	  	return path.all.substring(1, path.all.length);
	  };
	  
	  this.isActive = function(path) { 
	  	return $location.path() === path.all || $location.path().startsWith(path.singlePath);
	  };

  }]);
