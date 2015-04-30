"use strict";

angular.module("sharksClient")
.controller("FooterCtrl", ["contentservice", 
                                       function (contentservice) {
	  this.footer1 = contentservice.get("FOOTER_1");
	  this.footer2 = contentservice.get("FOOTER_2");
}]);