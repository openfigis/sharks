"use strict";

angular.module("sharksClient")
.controller("LoaderCtrl", ["$scope", function ($scope) {
	var self = this;
	this.showLoader = false; 
	
    function hideLoader() {
    	self.showLoader = false;
    }
    
    $scope.$on("$routeChangeStart", function() {
    	self.showLoader = true;
    });
    
    $scope.$on("$routeChangeSuccess",hideLoader);
    $scope.$on("$routeChangeError", hideLoader);
      
    // Initially element is hidden
    hideLoader();
}]);
