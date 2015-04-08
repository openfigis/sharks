"use strict";

angular.module("sharksClient")
  .controller("SearchCtrl", ["$log", "searchservice", function ($log, searchservice) {
	  var self = this;
	  this.searchValue = "";
	  this.searchResults = [];
	  this.search = function() {
		  $log.info("Search "+this.searchValue);
		  searchservice.query(this.searchValue).$promise.then(function(value) {
			  self.searchResults = value.response.docs;
		  });
	  };
  }]);
