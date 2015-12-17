"use strict";

angular.module("sharksClient").controller(
    "BrowseCtrl",
    [ "routingservice", "pageservice", "species", "groups", "entities", "countries",
        function(routingservice, pageservice, species, groups, entities, countries) {

          pageservice.setTitle("browse");

          this.species = Stream(species).sorted("englishName").toArray();
          this.groups = Stream(groups).sorted("name").toArray();
          this.entities = Stream(entities).sorted("acronym").toArray();

          this.countries = Stream(countries).filter(function(country) { // Taiwan is not shown in this presentation.
            return country.code !== "TWN";
          }).sorted("name").toArray();

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
        } ]);
