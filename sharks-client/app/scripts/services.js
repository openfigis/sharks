"use strict";

var services = angular.module("services", ["resources"]);


services.factory("speciesservice", ["speciesresource", "$q", function(speciesresource, $q) {
	
	function SpeciesService() {
		
		this.list = function() {
			return speciesresource.query();
		};
		
		this.get = function(alphaCode) {
			var deferred = $q.defer();
			speciesresource.query().$promise.then(function(species) {
				for (var i = 0; i < species.length; i++) {
				    if (alphaCode.indexOf(species[i].alphaCode) >= 0) deferred.resolve(species[i]);
				}
				deferred.reject("alphaCode "+alphaCode+" not found");
            });
			
			return deferred.promise;
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
		
		this.listGrouppedByEntity = function(alphaCode) {
			return measuresresource.groupByEntity({speciesAlphaCodes:[alphaCode]});
		};
	}
	return new MeasuresService();
	
}]);

services.factory("countriesservice", ["countriesresource", "$q", "$log", function(countriesresource, $q, $log) {
	
	function CountriesService() {
		this.selected = null;
		
		this.toggleSelection = function(country) {
			this.selected = country.code;
		};
		
		this.isSelected = function(country) {
			return this.selected === country.code;
		};
		
		this.list = function() {
			return countriesresource.query();
		};
		
		this.poasGroupedByType = function(code) {
			var deferred = $q.defer();
			
			countriesresource.poas({code:code}).$promise.then(function(poas) {
				var grouped = Stream(poas).groupBy(function (poa) {
				      return poa.poAType.description;
				  });
				deferred.resolve(grouped);
			});
			
			return deferred.promise;
		};
		
		this.entities = function(country) {
			return countriesresource.entities({code:country});
		};
		
		this.get = function(code) {
			var deferred = $q.defer();
			$log.info("get "+code);
			countriesresource.query().$promise.then(function(countries) {
				var found = Stream(countries).filter(function (country) {
				      return country.code === code;
				   }).findFirst();
				if (found.isPresent()) deferred.resolve(found.get());
				deferred.reject("Country with code "+code+" not found");
			});
			
			return deferred.promise;
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

services.factory("entitiesservice", ["entitiesresource", "$q", "$log", function(entitiesresource, $q, $log) {
	
	function EntitiesService() {
		this.selected = null;
		
		this.toggleSelection = function(entity) {
			$log.info("toggleSelection "+entity.acronym);
			this.selected = entity.acronym;
		};
		
		this.isSelected = function(entity) {
			return this.selected === entity.acronym;
		};
		
		this.list = function() {
			return entitiesresource.query();
		};
		
		this.measures = function(acronym) {
			return entitiesresource.measures({acronym:acronym});
		};
		
		this.countries = function(acronym) {
			return entitiesresource.countries({acronym:acronym});
		};
		
		this.getAll = function(acronyms) {
			var deferred = $q.defer();
			entitiesresource.query().$promise.then(function(entities) {
				var found = [];
				for (var i = 0; i < entities.length; i++) {
				    if (acronyms.indexOf(entities[i].acronym) >= 0) found.push(entities[i]);
				}
				deferred.resolve(found);
            });
			
			return deferred.promise;
		};
		
		this.get = function(acronym) {
			var deferred = $q.defer();
			entitiesresource.query().$promise.then(function(entities) {
				var found = null;
				for (var i = 0; i < entities.length; i++) {
				    if (entities[i].acronym === acronym) {
				    	found = entities[i];
				    	break;
				    }
				}
				deferred.resolve(found);
            });
			
			return deferred.promise;
		};
	}
	return new EntitiesService();
	
}]);

services.factory("searchservice", ["searchresource",  function(searchresource) {
	
	function SearchService() {
		
		this.query = function(query) {
			return searchresource.query({q:query});
		};
	}
	return new SearchService();
	
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
