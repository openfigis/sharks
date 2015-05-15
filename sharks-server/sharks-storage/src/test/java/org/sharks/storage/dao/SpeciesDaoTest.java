package org.sharks.storage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.TestProducers;
import org.sharks.storage.domain.Species;

@RunWith(CdiRunner.class)
@AdditionalClasses({TestProducers.class, SpeciesDaoImpl.class})
public class SpeciesDaoTest {

	@Inject
	SpeciesDao dao;

	@Test
	public void testListWithMeasures() {
		List<Species> species = dao.listWithMeasures();
		
		assertNotNull(species);
		assertEquals(12, species.size());
	}
	
	@Test
	public void testGetByAlphaCode() {
		Species species = dao.getByAlphaCode("ALV");
		
		assertNotNull(species);
		assertEquals("ALV", species.getAlphaCode());
		assertEquals(new Long(1), species.getCode());
	}
	
	@Test
	public void testGetByAlphaCodeWithWrongAlphaCode() {
		Species species = dao.getByAlphaCode("NOT_EXISTS");
		assertNull(species);
	}
	
}
