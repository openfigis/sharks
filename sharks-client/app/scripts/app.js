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
        templateUrl: "views/shark_species_selection.html",
        controller: "SharkSpeciesSelectionCtrl",
        controllerAs: "ctrl",
        resolve: {
        	species : function(speciesservice) {
        		return speciesservice.list();
        	}
        }
      })
      .when("/species/:alphaCodes", {
        templateUrl: "views/shark_species_details.html",
        controller: "SharkSpeciesDetailsCtrl",
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
      .when("/about", {
        templateUrl: "views/about.html",
        controller: "AboutCtrl"
      })
      .otherwise({
        redirectTo: "/species"
      });
  });
