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
  .constant("paths", {
				species: {
					all:"/species",
					single:"/species/:alphaCode",
					id: function(species) {return species.alphaCode;}
				},
				countries: {
					all:"/countries",
					single:"/countries/:code",
					id: function(country) {return country.code;}
				},
				entities: {
					all:"/entities",
					single:"/entities/:acronym",
					id: function(entity) {return entity.acronym;}
				},
				measures: {
					all:"/measures",
					single:"/measures/:id",
					id: function(measure) {return measure.id;}
				}
  })
  .config(function ($routeProvider, paths) {
    $routeProvider
      .when(paths.species.all, {
        templateUrl: "views/species_selection.html",
        controller: "SpeciesSelectionCtrl",
        controllerAs: "ctrl",
        resolve: {
        	species : function(speciesservice) {
        		return speciesservice.list();
        	}
        }
      })
      .when(paths.species.single, {
        templateUrl: "views/species_details.html",
        controller: "SpeciesDetailsCtrl",
        controllerAs: "ctrl",
        resolve: {
        	ems : function($route, measuresservice) {
        		return measuresservice.listGrouppedByEntity($route.current.params.alphaCode);
        	},
        	species : function($route, speciesservice) {
        		return speciesservice.get($route.current.params.alphaCode);
        	}
        }
      })
      
      .when(paths.countries.all, {
        templateUrl: "views/countries_selection.html",
        controller: "CountriesSelectionCtrl",
        controllerAs: "ctrl",
        resolve: {
        	countries : function(countriesservice) {
        		return countriesservice.list();
        	}
        }
      }) 
      .when(paths.countries.single, {
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
      
      
      .when(paths.entities.all, {
        templateUrl: "views/entities_selection.html",
        controller: "EntitiesSelectionCtrl",
        controllerAs: "ctrl",
        resolve: {
        	entities : function(entitiesservice) {
        		return entitiesservice.list();
        	}
        }
      }) 
      .when(paths.entities.single, {
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
      
      .when("/search", {
        templateUrl: "views/search.html",
        controller: "SearchCtrl",
        controllerAs: "ctrl"
      })
      
      .when(paths.measures.single, {
        templateUrl: "views/measure_details.html",
        controller: "MeasureDetailsCtrl",
        controllerAs: "ctrl",
        resolve: {
        	measure : function($route, measuresservice) {
        		return measuresservice.get($route.current.params.id);
        	}
        }
      })
      
      .otherwise({
        redirectTo: paths.species.all
      });
  });
