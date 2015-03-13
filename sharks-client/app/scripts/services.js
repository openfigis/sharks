"use strict";

var services = angular.module("services", ["ngResource","configuration"]);

services.factory("speciesservice", ["$resource", "config", function($resource, config) {
	return $resource(config.sharksWebUrl + "species/:id");
}]);