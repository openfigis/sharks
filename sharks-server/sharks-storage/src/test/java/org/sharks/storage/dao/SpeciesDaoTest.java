package org.sharks.storage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.TestProducers;
import org.sharks.storage.domain.Species;

@RunWith(CdiRunner.class)
@AdditionalClasses(TestProducers.class)
public class SpeciesDaoTest {

	@Inject
	SpeciesDao dao;

	/**
	 * Retrieves all the measures related to species, we expect measure 2 as result.
	 */
	@Test
	public void testListWithMeasures() {
		List<Species> species = dao.listWithMeasures();
		
		System.out.println(species);
		
		assertNotNull(species);
		assertEquals(12, species.size());
		

		List<Long> ids = species.stream().map(Species::getCode).collect(Collectors.toList());

		assertTrue(ids.contains(4l));
		assertTrue(ids.contains(15l));
	}
	
}
