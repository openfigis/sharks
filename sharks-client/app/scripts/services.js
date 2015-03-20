"use strict";

var services = angular.module("services", ["resources"]);


services.factory("speciesservice", ["$cacheFactory", "speciesresource", "$log", "$q", function($cacheFactory, speciesresource, $log, $q) {
	
	return {
		selected : [],
		
		list : function() {
			return speciesresource.query();
		},
		
		get : function(code) {
			var species = speciesresource.query();
			for (var i = 0; i < species.length; i++) {
			    if (species[i].code === code) return species[i];
			}
			return null;
		},
		
		getAll : function(codes) {
			var deferred = $q.defer();
			speciesresource.query().$promise.then(function(species) {
				var found = [];
				for (var i = 0; i < species.length; i++) {
				    if (codes.indexOf(species[i].code) >= 0) found.push(species[i]);
				}
				deferred.resolve(found);
            });
			
			return deferred.promise;
		}
	}
	
}]);
