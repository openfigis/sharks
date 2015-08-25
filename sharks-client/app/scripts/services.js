"use strict";

var services = angular.module("services", ["resources"]);

services.factory("speciesservice", ["speciesresource", function(speciesresource) {
	
	function SpeciesService() {
		
		this.list = function() {
			return speciesresource.query().$promise;
		};

		this.get = function(alphaCode) {
			return speciesresource.get({alphaCode:alphaCode}).$promise;
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

services.factory("entitiesservice", ["entitiesresource", function(entitiesresource) {
	
	function EntitiesService() {
		
		this.list = function() {
			return entitiesresource.query().$promise;
		};
				
		this.get = function(acronym) {
			return entitiesresource.get({acronym:acronym}).$promise;
		};
	}
	return new EntitiesService();
	
}]);

services.factory("advancedsearchservice", ["searchresource",  function(searchresource) {
	
	function AdvancedSearchService() {
		
		this.query = function(query) {
			return searchresource.query({q:query}).$promise;
		};
	}
	return new AdvancedSearchService();
	
}]);

services.factory("contentservice", ["contentresource",  function(contentresource) {
	
	function ContentService() {
		
		this.get = function(keyword) {
			return contentresource.query({keyword:keyword});
		};
	}
	return new ContentService();
	
}]);

services.factory("pageservice", ["titleComponents",  function(titleComponents) {
	
	function PageService() {
		
		this.title = "";
		
		this.setTitle = function(part) {
			this.title = titleComponents.base + " - " + titleComponents.middle + " - "+part;
		};
		
		this.setHomeTitle = function() {
			this.title = titleComponents.base + " - " + titleComponents.home;
		};
	}
	return new PageService();
	
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

services.factory("imagesservice", ["countryprofiles",function(countryprofiles) {
	
	function ImagesService() {
		
		var self = this;
		
		this.cleanName = function(name){
			return name.toLowerCase().replace(/ /g, "_");
		};
		
		this.missingThumbImageUrl = "images/species/missing-thumb.png";
		this.missingMediumImageUrl = "images/species/missing-medium.png";
		this.speciesMissingMediumImageUrl = "images/species/missing-medium.png";
		this.groupMissingMediumImageUrl = "images/species/missing-medium.png";
		this.missingFlagUrl = "images/no-flag.gif";
		
		this.speciesMediumImageUrl = function(species) {
			return "images/species/"+self.cleanName(species.scientificName)+"-drawing-medium.png"; 
		};
		
		this.speciesThumbImageUrl = function(species) {
			return "images/species/"+self.cleanName(species.scientificName)+"-drawing-small.jpg"; 
		};
		
		this.groupMediumImageUrl = function(group) {
			return "images/groups/"+self.cleanName(group.name)+"-drawing-medium.png"; 
		};

		this.groupThumbImageUrl = function(group) {
			return "images/groups/"+self.cleanName(group.name)+"-drawing-small.jpg"; 
		};
		
		this.countryFlagUrl = function(country) {
			return countryprofiles.flagBaseUrl+country.code+".gif";
		};
	
	}
	return new ImagesService();
	
}]);


services.factory("searchservice", ["$log", "$q", "speciesservice", "groupsservice", "countriesservice", "entitiesservice",
                                   function($log, $q, speciesservice, groupsservice, countriesservice, entitiesservice) {
	
	function SearchService() {
		
		var compare = function(a,b) {
			if (a === null || b === null) return false;
			return a.toLowerCase().indexOf(b) !== -1;
		};
		
		//{"alphaCode":"ALV","scientificName":"Alopias vulpinus","englishName":"Thresher","hasMeasures":true}
		/*var speciesFilter = function(species, term) {
			return compare(species.alphaCode, term) || 
			compare(species.englishName, term) || 
			compare(species.scientificName, term);
		};*/
		
		//{"code":1,"name":"Sharks"}
		var groupFilter = function(group, term) {
			return compare(group.name, term);
		};
		
		//{"acronym":"ICCAT","type":2}
		var entityFilter = function(entity, term) {
			return compare(entity.acronym, term);
		};
		
		//{"code":"EUR","name":"European Union","continent":null}
		var countryFilter = function(country, term) {
			return compare(country.code, term) || 
			compare(country.name, term);
		};
		
		this.search = function(query) {
			$log.info("search", query);
			
			var term = query.toLowerCase();
			var deferred = $q.defer();
			
			var speciesPromise = speciesservice.list();
			var groupsPromise = groupsservice.list();
			var entitiesPromise = entitiesservice.list();
			var countriesPromise = countriesservice.list();
			
			$q.all([speciesPromise, groupsPromise, entitiesPromise, countriesPromise]).then(function(data){
				
				var results = [];
				
				var species = data[0];
				Stream(species).forEach(function(species){
					
					if (compare(species.englishName, term) || 
						compare(species.alphaCode, term) ||
						compare(species.scientificName, term)) {
							results.push({
								entry: species,
								title: species.englishName,
								description: species.scientificName,
								type: "species"
							});
					}
					
					if (compare(species.frenchName, term)) {
						results.push({
							entry: species,
							title: species.frenchName,
							description: species.scientificName,
							type: "species"
						});
					}
					
					if (compare(species.spanishName, term)) {
						results.push({
							entry: species,
							title: species.spanishName,
							description: species.scientificName,
							type: "species"
						});
					}
					
					if (compare(species.arabicName, term)) {
						results.push({
							entry: species,
							title: species.arabicName,
							description: species.scientificName,
							type: "species"
						});
					}
					
					if (compare(species.chineseName, term)) {
						results.push({
							entry: species,
							title: species.chineseName,
							description: species.scientificName,
							type: "species"
						});
					}
					
					if (compare(species.russianName, term)) {
						results.push({
							entry: species,
							title: species.russianName,
							description: species.scientificName,
							type: "species"
						});
					}

				});
				
				var groups = data[1];
				Stream(groups)
					.filter(function(group){
						return groupFilter(group, term);
					})
					.forEach(function(group){
						results.push({
							entry: group,
							title: group.name,
							description: "",
							type: "groups"
						});
					});
				
				var entities = data[2];
				Stream(entities)
					.filter(function(entity){
						return entityFilter(entity, term);
					})
					.forEach(function(entity){
						results.push({
							entry: entity,
							title: entity.acronym,
							description: "",
							type: "entities"
						});
					});
				
				var countries = data[3];
				Stream(countries)
					.filter(function(country){
						return countryFilter(country, term);
					})
					.forEach(function(country){
						results.push({
							entry: country,
							title: country.name,
							description: country.continent,
							type: "countries"
						});
					});

				
				deferred.resolve({
					data:results
				});
			});

			
			return deferred.promise;
		};
		
		
	}
	return new SearchService();
	
}]);
