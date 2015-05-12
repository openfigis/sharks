"use strict";

angular.module("sharksClient")
.controller("BrowseCtrl", ["routingservice", "species", "groups", "entities", "countries",
                                     function (routingservice, species, groups, entities, countries) {
	  this.species = species;
	  this.groups = groups;
	  this.entities = entities;
	  this.countries = countries;
	  
	  this.showSpecies = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.showGroup = function(group) {
		  routingservice.toSingle("groups", group);
	  };
	  
	  this.showEntity = function(entity) {
		  routingservice.toSingle("entities", entity);
	  };
	  
	  this.showCountry = function(country) {
		  routingservice.toSingle("countries", country);
	  };

}]);
