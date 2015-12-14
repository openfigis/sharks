"use strict";

angular.module("sharksClient").controller("CountriesSelectionCtrl",
    [ "routingservice", "pageservice", "countries", "footer", function(routingservice, pageservice, countries, footer) {

      this.groupedCountries = Stream(countries).sorted(function(a, b) {
        if (a.code === "EUR")
          return 1;
        if (b.code === "EUR")
          return -1;

        if (a.name === b.name)
          return 0;
        if (a.name < b.name)
          return -1;
        return 1;
      }).filter(function(country) {
        // Taiwan is not shown in this presentation.
        return country.code !== "TWN";
      }).groupBy(function(country) {
        if (country.code === "EUR")
          return "Europe";
        return country.continent;
      });

      pageservice.setTitle("countries");
      this.footer = footer;

      this.show = function(country) {
        routingservice.toSingle("countries", country);
      };
    } ]);