"use strict";

var services = angular.module("services", ["resources"]);


services.factory("speciesservice", ["speciesresource", "$q", function(speciesresource, $q) {
	
	function SpeciesService() {
		
		this.list = function() {
			return speciesresource.query().$promise;
		};

		this.get = function(alphaCode) {
			return speciesresource.get({alphaCode:alphaCode}).$promise;
		};
		
		this.getAll = function(alphaCodes) {
			var deferred = $q.defer();
			speciesresource.list().$promise.then(function(species) {
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

services.factory("groupsservice", ["groupsresource", function(groupsresource) {
	
	function GroupsService() {
		
		this.list = function() {
			return groupsresource.query().$promise;
		};
		
		this.get = function(code) {
			return groupsresource.get({code:code}).$promise;
		};
	}
	return new GroupsService();
	
}]);



services.factory("measuresservice", ["measuresresource", function(measuresresource) {
	
	function MeasuresService() {
		
		this.listGrouppedByEntity = function(alphaCode) {
			return measuresresource.groupByEntity({speciesAlphaCodes:[alphaCode]}).$promise;
		};
		
		this.get = function(code) {
			return measuresresource.get({id:code}).$promise;
		};
	}
	return new MeasuresService();
	
}]);

services.factory("countriesservice", ["countriesresource", function(countriesresource) {
	
	function CountriesService() {
		
		this.list = function() {
			return countriesresource.query().$promise;
		};
		
		this.get = function(code) {
			return countriesresource.get({code:code}).$promise;
		};
	}
	return new CountriesService();
	
}]);

services.factory("entitiesservice", ["entitiesresource", "$q", function(entitiesresource, $q) {
	
	function EntitiesService() {
		
		this.list = function() {
			return entitiesresource.query().$promise;
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
			return entitiesresource.get({acronym:acronym}).$promise;
		};
	}
	return new EntitiesService();
	
}]);

services.factory("poasservice", ["poasresource", function(poasresource) {
	
	function PoaService() {
		
		this.get = function(code) {
			return poasresource.get({code:code}).$promise;
		};
	}
	return new PoaService();
	
}]);

services.factory("searchservice", ["searchresource",  function(searchresource) {
	
	function SearchService() {
		
		this.query = function(query) {
			return searchresource.query({q:query}).$promise;
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
			var hash = paths[type].singlePath+id;
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
		
		this.getArea = function() {
			for (var path in paths) {
				if ($location.path().startsWith(path.all) || $location.path().startsWith(path.singlePath)) return path;
			}
			return null;
		};
	}
	return new RoutingService();
	
}]);

services.factory("imagesservice", [function() {
	
	function ImagesService() {
		
		this.missingThumbImageUrl = "images/species/missing-thumb.png";
		this.speciesMissingSmallImageUrl = "images/species/missing-small.gif";
		this.speciesMissingMediumImageUrl = "images/species/missing-medium.gif";
		this.groupMissingMediumImageUrl = "images/species/missing-medium.gif";
		
		this.speciesSmallImageUrl = function(species) {
		  var sn = species.scientificName.toLowerCase().replace(" ", "_");
		  return "images/species/"+sn+"-drawing-small.gif";  
		};
		
		this.speciesMediumImageUrl = function(species) {
			var sn = species.scientificName.toLowerCase().replace(" ", "_");
			return "images/species/"+sn+"-drawing-medium.gif"; 
		};
		
		this.speciesThumbImageUrl = function(species) {
			var sn = species.scientificName.toLowerCase().replace(" ", "_");
			return "images/species/"+sn+"-drawing-thumb.png"; 
		};
		
		this.groupMediumImageUrl = function(group) {
			return "images/groups/"+group.code+"-drawing-medium.gif"; 
		};
		
		this.groupThumbImageUrl = function(group) {
			return "images/groups/"+group.code+"-drawing-thumb.png"; 
		};
		
	}
	return new ImagesService();
	
}]);
