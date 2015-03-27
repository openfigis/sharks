"use strict";

var services = angular.module("resources", ["ngResource","config"]);

services.factory("speciesresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "species/:id", null, {
		query: {method: "GET", cache: true, isArray:true}
	});
}]);

services.factory("measuresresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "measures/:id/", null, {
		groupByEntity: {method:"GET", url:rest.baseUrl + "measures/groupByEntity", isArray:true, cache: true}
	});
}]);

services.factory("countriesresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "countries/:id/", null, {
		query: {method: "GET", cache: true, isArray:true}
	});
}]);

services.factory("entitiesresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "managemententities/:acronym/", null, {
		query: {method: "GET", cache: true, isArray:true},
		measures: {method: "GET",  url:rest.baseUrl + "managemententities/:acronym/measures", cache: true, isArray:true},
	});
}]);