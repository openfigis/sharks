"use strict";

/**
 * @ngdoc function
 * @name sharksClient.controller:SharkSpeciesCtrl
 * @description
 * # SharkSpeciesCtrl
 * Controller of the sharksClient
 */
angular.module("sharksClient")
  .controller("SpeciesSelectionCtrl", ["routingservice", "imagesservice", "pageservice", "$timeout", "$scope", 
                                       "species", "groups", "footer", "isHome",
                                       function (routingservice, imagesservice, pageservice, $timeout, $scope, 
                                    		   species, groups, footer, isHome) {
	  
	  var self = this;
	  
	  this.species = Stream(species).sorted("scientificName").toArray();
	  this.groups = Stream(groups).sorted("name").toArray();

	  this.title = "";
	  this.subTitle = "";
	  
	  this.speciesIterator = Stream(groups).iterator();
	  this.onAnimationTimeout = function(){
		  var nextElement = self.speciesIterator.next();
		  var value = nextElement.value;
		  var isSpecies = value.scientificName!==undefined;
		  
		  if (isSpecies) self.hoverSpecies(value);
		  else self.hoverGroup(value);
		  
		  if (nextElement.done) self.speciesIterator = Stream(isSpecies?groups:species).iterator();
		  
		  self.animationTimeout = $timeout(self.onAnimationTimeout, 3000);
	  };
	  
	  this.animationTimeout = $timeout(self.onAnimationTimeout, 3000);
	    
	  this.stopAnimation = function(){
		  $timeout.cancel(this.animationTimeout);
	  };
	  
	  $scope.$on("$destroy", function(){
		  self.stopAnimation();
	  });
	  
	  if (isHome) pageservice.setHomeTitle();
	  else pageservice.setTitle("species");
	  this.footer = footer;
	  this.showUrls = isHome;
	  
	  this.imgUrl = "";
	  
	  this.showSpecies = function(species) {
		  routingservice.toSingle("species", species);
	  };
	  
	  this.showGroup = function(group) {
		  routingservice.toSingle("groups", group);
	  };
	  
	  this.hoverSpecies = function(species) {
		this.title = species.englishName;
		this.subTitle = species.scientificName;
		this.imgUrl = imagesservice.speciesMediumImageUrl(species);
	  };
	  
	  this.hoverGroup = function(group) {
		this.title = group.name;
		this.subTitle = "";
		this.imgUrl = imagesservice.groupMediumImageUrl(group);
	  };
	  
	  this.missingThumbUrl = imagesservice.missingThumbImageUrl;
	  this.missingImgUrl = imagesservice.missingMediumImageUrl;
	  this.speciesThumbUrl = imagesservice.speciesThumbImageUrl;
	  this.groupThumbUrl = imagesservice.groupThumbImageUrl;
  }]);
