"use strict";

describe("resource: speciesresource", function() {
	
	beforeEach(module("resources"));
	
	var speciesresource, $httpBackend, rest;
	
	beforeEach(inject(function(_speciesresource_, _$httpBackend_, _rest_) {
		speciesresource = _speciesresource_;
		$httpBackend = _$httpBackend_;
		rest = _rest_;
	}));
	
	
	describe("when querying the species", function() {
		
	   afterEach(function() {
	     $httpBackend.verifyNoOutstandingExpectation();
	     $httpBackend.verifyNoOutstandingRequest();
	   });
		
		it("should call the server", function() {
			$httpBackend.expectGET(rest.baseUrl+"species").respond([]);
			speciesresource.query();
			$httpBackend.flush();
			
		});
		
		it("should use the cache when called two times", function() {
			
			$httpBackend.expectGET(rest.baseUrl+"species").respond([]);
			speciesresource.query();
			
			speciesresource.query();
			$httpBackend.flush();
			$httpBackend.verifyNoOutstandingRequest();
		});
		
	});
});

describe("resource: measuresresource", function() {
	
	beforeEach(module("resources"));
	
	var measuresresource, $httpBackend, rest;
	
	beforeEach(inject(function(_measuresresource_, _$httpBackend_, _rest_) {
		measuresresource = _measuresresource_;
		$httpBackend = _$httpBackend_;
		rest = _rest_;
	}));
	
	
	describe("when querying the measures grouped by entity", function() {
		
	   afterEach(function() {
	     $httpBackend.verifyNoOutstandingExpectation();
	     $httpBackend.verifyNoOutstandingRequest();
	   });
		
		it("should call the server", function() {
			$httpBackend.expectGET(rest.baseUrl+"measures/groupByEntity").respond([]);
			measuresresource.groupByEntity();
			$httpBackend.flush();
			
		});
		
		it("should use the cache when called two times", function() {
			
			$httpBackend.expectGET(rest.baseUrl+"measures/groupByEntity").respond([]);
			measuresresource.groupByEntity();
			
			measuresresource.groupByEntity();
			$httpBackend.flush();
			$httpBackend.verifyNoOutstandingRequest();
		});
		
	});
});