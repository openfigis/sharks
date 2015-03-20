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
      .when("/species/:ids", {
        templateUrl: "views/shark_species_details.html",
        controller: "SharkSpeciesDetailsCtrl",
        controllerAs: "ctrl",
        resolve: {
        	ems : function($route, measuresresource) {
        		var ids = $route.current.params.ids.split(",").map(function(item) {
        		    return parseInt(item, 10);
        		});
        		return measuresresource.groupByEntity({species:ids});
        	},
        	species : function($route, speciesservice) {
        		var ids = $route.current.params.ids.split(",").map(function(item) {
        		    return parseInt(item, 10);
        		});
        		speciesservice.selected = ids;
        		return speciesservice.getAll(ids);
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
