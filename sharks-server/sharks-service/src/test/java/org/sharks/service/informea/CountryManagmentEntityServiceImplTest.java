package org.sharks.service.informea;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.service.cites.Base;

@RunWith(CdiRunner.class)
@AdditionalClasses(CountryManagmentEntityServiceImpl.class)
public class CountryManagmentEntityServiceImplTest extends Base {

	String iso3 = "DEU";

	@Inject
	CountryManagmentEntityService s;

	@Test
	public void test() {
		assertEquals(2, s.getMesForCountry(iso3).size());
	}

}
