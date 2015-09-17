package org.sharks.service.cites;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@AdditionalClasses(CitesServiceImpl.class)
public class CitesServiceImplTest extends Base {

	String iso3 = "DEU";

	@Inject
	CitesService citesService;

	@Test
	public void isMember() {
		assertTrue(citesService.isMember(iso3));
	}

	@Test
	public void getParties() {
		assertTrue(citesService.getParties().size() > 175);
	}

}