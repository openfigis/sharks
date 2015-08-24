"use strict";

angular.module("sharksClient")
  .controller("NavigationBarCtrl", ["$location", "$log", "paths",
                                    "searchservice", "routingservice",
                                         function ($location, $log, paths, searchservice, routingservice) {
	  this.paths = paths;

	  this.href = function(path) { 
	  	return path.all.substring(1, path.all.length);
	  };
	  
	  this.isActive = function(path) { 
	  	return $location.path() === path.all || $location.path().startsWith(path.singlePath);
	  };
	  
	  this.search = searchservice.search;
	  
	  this.selectedItem = function(item) {
		  $log.info("selected item", item);
		  
		  var type = item.originalObject.type;
		  var entry = item.originalObject.entry; 
		  routingservice.toSingle(type, entry);
		 
	  };
  }]);
