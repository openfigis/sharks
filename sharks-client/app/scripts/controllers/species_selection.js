"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesSelectionCtrl", ["routingservice", "imagesservice", "species", "groups", "footer", "showUrls",
                                       function (routingservice, imagesservice, species, groups, footer, showUrls) {
	  this.species = species;
	  this.groups = groups;

	  this.title = "";
	  this.subTitle = "";
	  
	  this.footer = footer;
	  this.showUrls = showUrls;
	  
	  this.show = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.hoverSpecies = function(species) {
		this.title = species.nameEn;
		this.subTitle = species.scientificName;
	  };
	  
	  this.hoverGroup = function(group) {
		this.title = group.customSpeciesGrp;
		this.subTitle = "";
	  };
	  
	  this.imageUrl = imagesservice.speciesSmallImageUrl;
	  this.missingImageUrl = imagesservice.speciesMissingSmallImageUrl;
	  
	  this.missingThumbUrl = imagesservice.missingThumbImageUrl;
	  this.speciesThumbUrl = imagesservice.speciesThumbImageUrl;
  }]);
