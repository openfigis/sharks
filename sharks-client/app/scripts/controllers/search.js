"use strict";

angular.module("sharksClient")
  .controller("SearchCtrl", ["$log", "routingservice", "searchservice", function ($log, routingservice, searchservice) {
	  var self = this;
	  this.searchValue = "";
	  this.searchResults = [];
	  this.search = function() {
		  $log.info("Search "+this.searchValue);
		  searchservice.query(this.searchValue).$promise.then(function(value) {
			  self.searchResults = value.response.docs;
		  });
	  };
	  
	  this.showItem = function(item) {
		  if (item.documentType[0] === "Measure") routingservice.toSingleById("measures", item.id);
	  };
  }]);
