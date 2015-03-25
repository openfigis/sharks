describe("service: speciesservice", function() {
	

	var mockSpecies1 = {alphaCode: "1"};
	var mockSpecies2 = {alphaCode: "2"};
	
	beforeEach(module('services'));
	
	var speciesservice;

	beforeEach(inject(function(_speciesservice_, speciesresource, $q) {
		speciesservice = _speciesservice_;
		spyOn(speciesresource, "query").and.returnValue({ $promise: $q.when([mockSpecies1, mockSpecies2]) });
	}));
	
	describe("selection", function() {
		it("start with empty selection", function() {
			expect(speciesservice.selected.length).toBe(0);
		})
		
		it("toggle selection", function() {
			expect(speciesservice.selected.length).toBe(0);
			expect(speciesservice.isSelected(mockSpecies1)).toBeFalsy();
			
			speciesservice.toggleSelection(mockSpecies1);
			
			expect(speciesservice.selected.length).toBe(1);
			expect(speciesservice.isSelected(mockSpecies1)).toBeTruthy();
			
			speciesservice.toggleSelection(mockSpecies1);
			
			expect(speciesservice.selected.length).toBe(0);
			expect(speciesservice.isSelected(mockSpecies1)).toBeFalsy();
		});
	});
	
	/*describe("getAll", function() {
		it("get all the species", inject(function($rootScope) {
			$rootScope.$apply();
			expect(speciesservice.getAll([mockSpecies1.alphaCode]).length).toBe(1);
		}));
	});*/


		

});


describe("service: pathservice", function() {
	
	beforeEach(module('services'));
	
	var pathservice;
	
	beforeEach(inject(function(_pathservice_) {
		pathservice = _pathservice_;
	}));
	
	var IDS = ["1","2","3"];
	var PARAM = "1,2,3";
	
		
	it("should decode", function() {
		var ids = pathservice.decode(PARAM);
		expect(ids).toEqual(IDS);
	});
	
	it("should encode", function() {
		var param = pathservice.encode(IDS);
		expect(param).toBe(PARAM);
	});
		

});