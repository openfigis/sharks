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
    "services"
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when("/species", {
        templateUrl: "views/shark_species_selection.html",
        controller: "SharkSpeciesSelectionCtrl",
        controllerAs: "ctrl"
      })
      .when("/species/:ids", {
        templateUrl: "views/shark_species_details.html",
        controller: "SharkSpeciesDetailsCtrl",
        controllerAs: "ctrl"
      })
      .when("/about", {
        templateUrl: "views/about.html",
        controller: "AboutCtrl"
      })
      .otherwise({
        redirectTo: "/species"
      });
  });
