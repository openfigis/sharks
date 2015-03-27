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
      
      
      .when("/about", {
        templateUrl: "views/about.html",
        controller: "AboutCtrl"
      })
      .otherwise({
        redirectTo: "/species"
      });
  });
