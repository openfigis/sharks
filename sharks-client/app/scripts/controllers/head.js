"use strict";

angular.module("sharksClient")
.controller("HeadCtrl", ["pageservice", "$scope", function (pageservice, $scope) {
	$scope.page = pageservice;
}]);