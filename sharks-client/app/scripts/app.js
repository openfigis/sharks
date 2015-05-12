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
				home: {
					all: "/home",
					footerKey: "HOME_FOOTER"
				},
				browse: {
					all: "/browse"
				},
				species: {
					all:"/species",
					single:"/species/:alphaCode",
					singlePath:"/species/",
					footerKey: "SPECIES_FOOTER",
					id: function(species) {return species.alphaCode;}
				},
				countries: {
					all:"/countries",
					single:"/country/:code",
					singlePath:"/country/",
					footerKey: "COUNTRIES_FOOTER",
					id: function(country) {return country.code;}
				},
				entities: {
					all:"/rfbs",
					single:"/rfb/:acronym",
					singlePath:"/rfb/",
					footerKey: "ENTITIES_FOOTER",
					id: function(entity) {return entity.acronym;}
				},
				measures: {
					all:"/measures",
					single:"/measure/:id",
					singlePath:"/measure/",
					id: function(measure) {return measure.id;}
				},
				poas: {
					all:"/poas",
					single:"/poas/:code",
					singlePath:"/poa/",
					id: function(poa) {return poa.code;}
				}
  })
  .config(function ($routeProvider, paths) {
    $routeProvider
    .when(paths.home.all, {
        templateUrl: "views/species_selection.html",
        controller: "SpeciesSelectionCtrl",
        controllerAs: "ctrl",
        resolve: {
        	species : function(speciesservice) {
        		return speciesservice.list();
        	},
      		groups : function(groupsservice) {
      			return groupsservice.list();
      		},
      		footer : function(contentservice) {
      			return contentservice.get(paths.home.footerKey);
      		},
      		showUrls: function() {
      			return true;
      		}
        }
      })
    .when(paths.browse.all, {
        templateUrl: "views/browse.html",
        controller: "BrowseCtrl",
        controllerAs: "ctrl",
        resolve: {
        	species : function(speciesservice) {
        		return speciesservice.list();
        	},
      		groups : function(groupsservice) {
      			return groupsservice.list();
      		},
      		countries : function(countriesservice) {
        		return countriesservice.list();
        	},
        	entities : function(entitiesservice) {
        		return entitiesservice.list();
        	}
        }
      })
      .when(paths.species.all, {
        templateUrl: "views/species_selection.html",
        controller: "SpeciesSelectionCtrl",
        controllerAs: "ctrl",
        resolve: {
        	species : function(speciesservice) {
        		return speciesservice.list();
        	},
      		groups : function(groupsservice) {
      			return groupsservice.list();
      		},
      		footer : function(contentservice) {
      			return contentservice.get(paths.species.footerKey);
      		},
      		showUrls: function() {
      			return false;
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
        	groupedCountries : function(countriesservice) {
        		return countriesservice.countriesGroupedByContinent();
        	},
      		footer : function(contentservice) {
      			return contentservice.get(paths.countries.footerKey);
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
        	},
      		footer : function(contentservice) {
      			return contentservice.get(paths.entities.footerKey);
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
      
      .when(paths.poas.single, {
        templateUrl: "views/poa_details.html",
        controller: "PoaDetailsCtrl",
        controllerAs: "ctrl",
        resolve: {
        	poa : function($route, poasservice) {
        		return poasservice.get($route.current.params.code);
        	}
        }
      })
      
      .otherwise({
        redirectTo: paths.home.all
      });
  })
  .config(function ($locationProvider) {
	  $locationProvider.html5Mode(true);
  })
  //missing images handling
  .directive("errSrc", function() {
  return {
	    link: function(scope, element, attrs) {
	      element.bind("error", function() {
	        if (attrs.src !== attrs.errSrc) {
	          attrs.$set("src", attrs.errSrc);
	        }
	      });
	    }
	  };
	});
