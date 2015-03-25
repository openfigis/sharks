"use strict";

var services = angular.module("services", ["resources"]);


services.factory("speciesservice", ["speciesresource", "$log", "$q", function(speciesresource, $log, $q) {
	
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


services.factory("measuresservice", ["measuresresource", "$log", function(measuresresource, $log) {
	
	function MeasuresService() {
		
		this.listGrouppedByEntity = function(alphaCodes) {
			return measuresresource.groupByEntity({speciesAlphaCodes:alphaCodes});
		};
	}
	return new MeasuresService();
	
}]);


services.factory("pathservice", ["$log", function($log) {
	
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
