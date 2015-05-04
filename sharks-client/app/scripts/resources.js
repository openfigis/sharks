"use strict";

var resources = angular.module("resources", ["ngResource","config"]);

resources.factory("speciesresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "species/:id", null, {
		query: {method: "GET", cache: true, params: {onlyWithMeasure:true}, isArray:true}
	});
}]);

resources.factory("groupsresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "groups", null, {
		query: {method: "GET", cache: true, isArray:true}
	});
}]);

resources.factory("measuresresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "measures/:id/", null, {
		query: {method: "GET", cache: true},
		groupByEntity: {method:"GET", url:rest.baseUrl + "measures/groupByEntity", isArray:true, cache: true}
	});
}]);

resources.factory("countriesresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "countries/:code/", null, {
		query: {method: "GET", cache: true},
		list: {method: "GET", cache: true, params: {onlyWithPoAs:true},isArray:true},
		poas: {method: "GET",  url:rest.baseUrl + "countries/:code/poas", cache: true, isArray:true},
		entities: {method: "GET",  url:rest.baseUrl + "countries/:code/entities", cache: true, isArray:true}
	});
}]);

resources.factory("entitiesresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "managemententities/:acronym/", null, {
		query: {method: "GET", cache: true, isArray:true},
		measures: {method: "GET",  url:rest.baseUrl + "managemententities/:acronym/measures", cache: true, isArray:true},
		countries: {method: "GET",  url:rest.baseUrl + "managemententities/:acronym/countries", cache: true, isArray:true}
	});
}]);

resources.factory("poasresource", ["$resource", "rest", function($resource, rest) {
	return $resource(rest.baseUrl + "poas/:code/", null, {
		query: {method: "GET", cache: true}
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