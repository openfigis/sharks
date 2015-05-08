"use strict";

angular.module("sharksClient")
	.controller("FooterCtrl", ["footerservice", function (footerservice) {
		this.footerservice = footerservice;
	}]);