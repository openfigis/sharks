"use strict";

angular.module("sharksClient")
.directive("loader", function() {
  return {
	  restrict: "E",
	  templateUrl: "partials/loader.html",
	  link: function(scope, element) {
	      // Store original display mode of element
	      var shownType = element.css("display");
	      function hideElement() {
	        element.css("display", "none");
	      }
	            
	      scope.$on("$routeChangeStart", function() {
	        element.css("display", shownType);
	      });
	      scope.$on("$routeChangeSuccess",hideElement);
	      scope.$on("$routeChangeError", hideElement);
	      // Initially element is hidden
	      hideElement();
	    }
  };
});
