"use strict";

angular.module("sharksClient")
.directive("measure", function() {
  return {
	  restrict: "E",
	  scope:{value: "="},
	  templateUrl: "partials/measure.html"
  };
});
