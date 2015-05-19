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
	  
	  this.species = Stream(species).sorted("englishName").toArray();
	  this.groups = Stream(groups).sorted("name").toArray();

	  this.title = "";
	  this.subTitle = "";
	  
	  this.footer = footer;
	  this.showUrls = showUrls;
	  
	  this.imgUrl = "images/TMP_SPECIES_SELECTOR.png";
	  
	  this.showSpecies = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.showGroup = function(group) {
		  routingservice.toSingle("groups", group);
	  };
	  
	  this.hoverSpecies = function(species) {
		this.title = species.englishName;
		this.subTitle = species.scientificName;
	  };
	  
	  this.hoverGroup = function(group) {
		this.title = group.name;
		this.subTitle = "";
	  };
	  
	  this.missingThumbUrl = imagesservice.missingThumbImageUrl;
	  this.speciesThumbUrl = imagesservice.speciesThumbImageUrl;
	  this.groupThumbUrl = imagesservice.groupThumbImageUrl;
  }]);
