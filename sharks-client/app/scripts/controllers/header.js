"use strict";

angular.module("sharksClient")
.controller("HeaderCtrl", ["contentservice", 
                                       function (contentservice) {
	  this.title = contentservice.get("TITLE");
}]);