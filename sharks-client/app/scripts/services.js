"use strict";

var services = angular.module("services", ["ngResource","configuration"]);

services.factory("speciesservice", ["$resource", "config", function($resource, config) {
	return $resource(config.sharksWebUrl + "species/:id");
}]);

services.factory("measuresservice", ["$resource", "config", function($resource, config) {
	return $resource(config.sharksWebUrl + "measures/:id/", null, {
		groupByEntity: {method:'GET', url:config.sharksWebUrl + "measures/groupByEntity", isArray:true}
	});
}]);