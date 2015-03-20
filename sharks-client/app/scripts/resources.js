"use strict";

var services = angular.module("resources", ["ngResource","configuration"]);

services.factory("speciesresource", ["$resource", "config", function($resource, config) {
	return $resource(config.sharksWebUrl + "species/:id", null, {
		query: {
            method: "GET",
            cache: true, 
            isArray:true
        }
		
	});
}]);

services.factory("measuresresource", ["$resource", "config", function($resource, config) {
	return $resource(config.sharksWebUrl + "measures/:id/", null, {
		groupByEntity: {method:'GET', url:config.sharksWebUrl + "measures/groupByEntity", isArray:true, cache: true}
	});
}]);