package org.sharks.service.cites;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
public class CitesServiceImplTest extends Base {

	String iso3 = "DEU";

	@Inject
	CitesServiceImpl cites;

	@Test
	public void getMember() {
		assertTrue(cites.getMember(iso3).get().getIso3().equals(iso3));
		assertFalse(cites.getMember("").isPresent());
	}

	@Test
	public void getParties() {
		assertTrue(cites.getParties().size() > 170);
	}
}