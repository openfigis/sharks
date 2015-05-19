"use strict";

angular.module("sharksClient")
.controller("TitleCtrl", ["pageservice", "$scope", function (pageservice, $scope) {
	$scope.page = pageservice;
}]);