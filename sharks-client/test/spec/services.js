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