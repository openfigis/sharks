"use strict";

/**
 * @ngdoc overview
 * @name sharksClientApp
 * @description
 * # sharksClientApp
 *
 * Main module of the application.
 */
angular
  .module("sharksClient", [
    "ngResource",
    "ngRoute",
    "resources",
    "services"
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when("/species", {
        templateUrl: "views/species_selection.html",
        controller: "SpeciesSelectionCtrl",
        controllerAs: "ctrl",
        resolve: {
        	species : function(speciesservice) {
        		return speciesservice.list();
        	}
        }
      })
      .when("/species/:alphaCodes", {
        templateUrl: "views/species_details.html",
        controller: "SpeciesDetailsCtrl",
        controllerAs: "ctrl",
        resolve: {
        	ems : function($route, measuresservice, pathservice) {
        		var alphaCodes = pathservice.decode($route.current.params.alphaCodes);
        		return measuresservice.listGrouppedByEntity(alphaCodes);
        	},
        	species : function($route, speciesservice, pathservice) {
        		var alphaCodes = pathservice.decode($route.current.params.alphaCodes);
        		speciesservice.selected = alphaCodes;
        		return speciesservice.getAll(alphaCodes);
        	}
        }
      })
      
      .when("/countries", {
        templateUrl: "views/countries_selection.html",
        controller: "CountriesSelectionCtrl",
        controllerAs: "ctrl",
        resolve: {
        	countries : function(countriesservice) {
        		return countriesservice.list();
        	}
        }
      }) 
      .when("/countries/:code", {
        templateUrl: "views/country_details.html",
        controller: "CountryDetailsCtrl",
        controllerAs: "ctrl",
        resolve: {
        	country : function($route, countriesservice) {
        		return countriesservice.get($route.current.params.code);
        	},
        	groupedPoas : function($route, countriesservice) {
        		return countriesservice.poasGroupedByType($route.current.params.code);
        	},
        	entities :  function($route, countriesservice) {
        		return countriesservice.entities($route.current.params.code);
        	}
        }
      })
      
      
      .when("/entities", {
        templateUrl: "views/entities_selection.html",
        controller: "EntitiesSelectionCtrl",
        controllerAs: "ctrl",
        resolve: {
        	entities : function(entitiesservice) {
        		return entitiesservice.list();
        	}
        }
      }) 
      .when("/entities/:acronym", {
        templateUrl: "views/entity_details.html",
        controller: "EntityDetailsCtrl",
        controllerAs: "ctrl",
        resolve: {
        	entity : function($route, entitiesservice) {
        		return entitiesservice.get($route.current.params.acronym);
        	},
        	measures : function($route, entitiesservice) {
        		return entitiesservice.measures($route.current.params.acronym);
        	},
        	countries : function($route, entitiesservice) {
        		return entitiesservice.countries($route.current.params.acronym);
        	}
        }
      })
      
      .when("/speciesid", {
        templateUrl: "views/coming_soon.html",
        controller: "ComingSoonCtrl"
      })

      .when("/links", {
        templateUrl: "views/coming_soon.html",
        controller: "ComingSoonCtrl"
      })
      
      .otherwise({
        redirectTo: "/species"
      });
  });
