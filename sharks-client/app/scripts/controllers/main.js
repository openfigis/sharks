'use strict';

/**
 * @ngdoc function
 * @name sharksClientApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sharksClientApp
 */
angular.module('sharksClientApp')
  .controller('MainCtrl', ["speciesservice", function (speciesservice) {
	  this.species = speciesservice.query();
  }]);
