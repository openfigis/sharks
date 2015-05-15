package org.sharks.storage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.TestProducers;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({TestProducers.class, CountryDaoImpl.class})
public class CountryDaoTest {

	@Inject
	CountryDao dao;

	/**
	 * Retrieves all the countries with almost one PoA connected.
	 */
	@Test
	public void testListWithPoAs() {
		List<Country> countries = dao.listWithPoAs();
		assertNotNull(countries);
		assertEquals(37, countries.size());
	}
}
