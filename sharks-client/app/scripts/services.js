"use strict";

var services = angular.module("services", ["resources"]);


services.factory("speciesservice", ["$cacheFactory", "speciesresource", "$log", "$q", function($cacheFactory, speciesresource, $log, $q) {
	
	function SpeciesService() {
		this.selected = [];
		
		this.toggleSelection = function(species) {
			var index = this.selected.indexOf(species.code);
			if (index < 0) this.selected.push(species.code);
			else this.selected.splice(index, 1);
		};
		
		this.isSelected = function(species) {
			return this.selected.indexOf(species.code)>=0;
		};
		
		this.list = function() {
			return speciesresource.query();
		};
		
		this.get = function(code) {
			var species = speciesresource.query();
			for (var i = 0; i < species.length; i++) {
			    if (species[i].code === code) return species[i];
			}
			return null;
		};
		
		this.getAll = function(codes) {
			var deferred = $q.defer();
			speciesresource.query().$promise.then(function(species) {
				var found = [];
				for (var i = 0; i < species.length; i++) {
				    if (codes.indexOf(species[i].code) >= 0) found.push(species[i]);
				}
				deferred.resolve(found);
            });
			
			return deferred.promise;
		};
	}
	return new SpeciesService();
	
}]);
