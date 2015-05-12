"use strict";

angular.module("sharksClient")
.directive("viewFooter", function() {
  return {
	  restrict: "E",
	  scope:{text: "@", showUrls: "@"},
	  templateUrl: "partials/view_footer.html"
  };
});
