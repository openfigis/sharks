"use strict";

var services = angular.module("services", ["resources"]);


services.factory("speciesservice", ["speciesresource", "$q", function(speciesresource, $q) {
	
	function SpeciesService() {
		this.selected = [];
		
		this.toggleSelection = function(species) {
			var index = this.selected.indexOf(species.alphaCode);
			if (index < 0) this.selected.push(species.alphaCode);
			else this.selected.splice(index, 1);
		};
		
		this.isSelected = function(species) {
			return this.selected.indexOf(species.alphaCode)>=0;
		};
		
		this.list = function() {
			return speciesresource.query();
		};
		
		this.getAll = function(alphaCodes) {
			var deferred = $q.defer();
			speciesresource.query().$promise.then(function(species) {
				var found = [];
				for (var i = 0; i < species.length; i++) {
				    if (alphaCodes.indexOf(species[i].alphaCode) >= 0) found.push(species[i]);
				}
				deferred.resolve(found);
            });
			
			return deferred.promise;
		};
	}
	return new SpeciesService();
	
}]);


services.factory("measuresservice", ["measuresresource", function(measuresresource) {
	
	function MeasuresService() {
		
		this.listGrouppedByEntity = function(alphaCodes) {
			return measuresresource.groupByEntity({speciesAlphaCodes:alphaCodes});
		};
	}
	return new MeasuresService();
	
}]);

services.factory("countriesservice", ["countriesresource", "$q", function(countriesresource, $q) {
	
	function CountriesService() {
		this.selected = [];
		
		this.toggleSelection = function(country) {
			var index = this.selected.indexOf(country.code);
			if (index < 0) this.selected.push(country.code);
			else this.selected.splice(index, 1);
		};
		
		this.isSelected = function(country) {
			return this.selected.indexOf(country.code)>=0;
		};
		
		this.list = function() {
			return countriesresource.query();
		};
		
		this.getAll = function(codes) {
			var deferred = $q.defer();
			countriesresource.query().$promise.then(function(countries) {
				var found = [];
				for (var i = 0; i < countries.length; i++) {
				    if (codes.indexOf(countries[i].code) >= 0) found.push(countries[i]);
				}
				deferred.resolve(found);
            });
			
			return deferred.promise;
		};
	}
	return new CountriesService();
	
}]);

services.factory("mgmentitiesservice", ["mgmentitiesresource", "$q", function(mgmentitiesresource, $q) {
	
	function ManagementEntitiesService() {
		this.selected = [];
		
		this.toggleSelection = function(entity) {
			var index = this.selected.indexOf(entity.acronym);
			if (index < 0) this.selected.push(entity.acronym);
			else this.selected.splice(index, 1);
		};
		
		this.isSelected = function(country) {
			return this.selected.indexOf(country.code)>=0;
		};
		
		this.list = function() {
			return mgmentitiesresource.query();
		};
		
		this.getAll = function(acronyms) {
			var deferred = $q.defer();
			mgmentitiesresource.query().$promise.then(function(entities) {
				var found = [];
				for (var i = 0; i < entities.length; i++) {
				    if (acronyms.indexOf(entities[i].acronym) >= 0) found.push(entities[i]);
				}
				deferred.resolve(found);
            });
			
			return deferred.promise;
		};
	}
	return new ManagementEntitiesService();
	
}]);



services.factory("pathservice", [function() {
	
	function PathService() {
		
		this.decode = function(param) {
			return param.split(",");
		};
		
		this.encode = function(ids) {
			return ids.join(",");
		};
	}
	return new PathService();
	
}]);
