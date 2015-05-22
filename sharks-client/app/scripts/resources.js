"use strict";

var resources = angular.module("resources", ["ngResource","config"]);

resources.factory("speciesresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "species/:alphaCode", null, {
		get: {method: "GET", cache: true},
		query: {method: "GET", cache: true, params: {onlyWithMeasure:true}, isArray:true}
	});
}]);

resources.factory("groupsresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "groups/:code", null, {
		get: {method: "GET", cache: true},
		query: {method: "GET", cache: true, isArray:true}
	});
}]);

resources.factory("countriesresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "countries/:code/", null, {
		get: {method: "GET", cache: true},
		query: {method: "GET", cache: true, params: {onlyWithPoAsOrOthers:true},isArray:true}
	});
}]);

resources.factory("entitiesresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "managemententities/:acronym/", null, {
		get: {method: "GET", cache: true},
		query: {method: "GET", cache: true, isArray:true}
	});
}]);

resources.factory("searchresource", ["$resource", "solr", function($resource, solr) {
	return $resource(solr.baseUrl + "select", null, {
		query: {method: "JSONP", cache: true, params: {wt:"json", "json.wrf": "JSON_CALLBACK"}},
	});
}]);

resources.factory("contentresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "contents/:keyword/", null, {
		query: {method: "GET", cache: true}
	});
}]);