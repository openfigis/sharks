"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description # SharkSpeciesCtrl Controller of the sharksClient
 */
angular.module("sharksClient").controller(
		"SpeciesDetailsCtrl",
		[
				"routingservice",
				"imagesservice",
				"pageservice",
				"factsheets",
				"mapViewer",
				"species",
				function(routingservice, imagesservice, pageservice, factsheets, mapViewer, species) {

					pageservice.setTitle(species.officialNames.en);
					pageservice.setDescription("List of conservation and management measures for " +
							 species.officialNames.en +
							 " issued by regional fisheries bodies or relevant conventions");

					this.species = species;
					this.ems = Stream(species.measures).sort(function(a, b) {
						if (a.year === b.year)
							return 0;
						if (a.year > b.year)
							return -1;
						return 1;
					}).groupBy(function(measure) {
						return measure.entityAcronym;
					});

					this.factsheetsUrl = species.figisId !== null ? factsheets.speciesBaseUrl +
							 species.figisId : null;

					this.showEntity = function(acronym) {
						routingservice.toSingleById("entities", acronym);
					};

					this.imageUrl = imagesservice.speciesMediumImageUrl;
					this.missingImageUrl = imagesservice.speciesMissingMediumImageUrl;

					this.mapUrl = mapViewer.speciesBaseUrl + "?species=" + species.alphaCode + "-m&prj=4326";

					if (species.hasDistributionMap) {
						FigisMap.draw({
							context : "FI-species",
							target : "distributionMap",
							mapSize : "L",
							distribution : [ {
								layer : "fifao:SPECIES_DIST",
								filter : "ALPHACODE='" + species.alphaCode + "'",
								title : "Abramis brama",
								autoZoom : true
							} ],
							legend : "mapSpeciesLegend",
							staticLabels : {
								"MAIN LAYERS" : "Species distribution"
							}
						});
					}
				} ]);
