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
		
		this.get = function(code) {
			return measuresresource.query({id:code});
		};
	}
	return new MeasuresService();
	
}]);

services.factory("countriesservice", ["countriesresource", "$q", function(countriesresource, $q) {
	
	function CountriesService() {
		
		this.list = function() {
			return countriesresource.list();
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
			return countriesresource.query({code:code});
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

services.factory("entitiesservice", ["entitiesresource", "$q", function(entitiesresource, $q) {
	
	function EntitiesService() {
		
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

services.factory("poasservice", ["poasresource", function(poasresource) {
	
	function PoaService() {
		
		this.get = function(code) {
			return poasresource.query({code:code});
		};
	}
	return new PoaService();
	
}]);

services.factory("searchservice", ["searchresource",  function(searchresource) {
	
	function SearchService() {
		
		this.query = function(query) {
			return searchresource.query({q:query});
		};
	}
	return new SearchService();
	
}]);

services.factory("contentservice", ["contentresource",  function(contentresource) {
	
	function ContentService() {
		
		this.get = function(keyword) {
			return contentresource.query({keyword:keyword});
		};
	}
	return new ContentService();
	
}]);



services.factory("routingservice", ["paths", "$location", "$window", "$log", function(paths, $location, $window, $log) {
	
	function RoutingService() {
		
		this.toAll = function(type) {
		  var hash = paths[type].all;
		  $log.info("show "+type+" routing to "+hash);
		  $location.path(hash);
		};
		
		this.toSingleById = function(type, id) {
			var hash = paths[type].all+"/"+id;
			$log.info("show "+type+" with id "+id+" routing to "+hash);
			$location.path(hash);
		};
		
		this.toSingle = function(type, item) {
			var id = paths[type].id(item);
			this.toSingleById(type,id);
		};
		
		this.goBack = function() {
		  $log.info("go back");
		  $window.history.back();
		};
	}
	return new RoutingService();
	
}]);

services.factory("imagesservice", [function() {
	
	function ImagesService() {
		
		this.speciesMissingSmallImageUrl = "images/species/missing-small.gif";
		this.speciesMissingMediumImageUrl = "images/species/missing-medium.gif";
		
		this.speciesSmallImageUrl = function(species) {
		  var sn = species.scientificName.toLowerCase().replace(" ", "_");
		  return "images/species/"+sn+"-drawing-small.gif";  
		};
		
		this.speciesMediumImageUrl = function(species) {
		  var sn = species.scientificName.toLowerCase().replace(" ", "_");
		  return "images/species/"+sn+"-drawing-medium.gif";  
		};
	}
	return new ImagesService();
	
}]);
