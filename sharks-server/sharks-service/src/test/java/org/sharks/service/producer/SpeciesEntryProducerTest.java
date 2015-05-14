/**
 * 
 */
package org.sharks.service.producer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestModelUtils.*;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.dto.SpeciesEntry;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
public class SpeciesEntryProducerTest {
	
	
	@Inject
	SpeciesEntryProducer producer;
	
	RefPubSpecies aSpecies;
	
	@Produces
	protected RefPubService setupRefPubService() {
		RefPubService service = Mockito.mock(RefPubService.class);
		aSpecies = aRefPubSpecies();
		when(service.getSpecies("ALV")).thenReturn(aSpecies);
		return service;
	}

	/**
	 * Test method for {@link org.sharks.service.producer.SpeciesEntryProducer#produce(org.sharks.storage.domain.Species)}.
	 * Checks if the data is substituted with the one from the ref pub service.
	 */
	@Test
	public void testProduceUsingRefPubService() {
		Species species = buildSpecies("ALV", "A_SCIENTIFIC_NAME");
		
		SpeciesEntry entry = producer.produce(species);
		assertNotNull(entry);
		assertEquals("ALV", entry.getAlphaCode());
		
		//the scientific name has been updated with the one from ref pub service
		assertNotNull(entry.getScientificName());
		assertEquals(aSpecies.getScientificName(), entry.getScientificName());
	}
	
	/**
	 * Test method for {@link org.sharks.service.producer.SpeciesEntryProducer#produce(org.sharks.storage.domain.Species)}.
	 * Checks if the data is not changed if is not present in the ref pub service.
	 */
	@Test
	public void testProduceWithouthRefPubService() {
		Species species = buildSpecies("LOCAL", "A_SCIENTIFIC_NAME");
		
		SpeciesEntry entry = producer.produce(species);
		assertNotNull(entry);
		assertEquals("LOCAL", entry.getAlphaCode());
		
		//the scientific name has NOT been updated
		assertNotNull(entry.getScientificName());
		assertEquals("A_SCIENTIFIC_NAME", entry.getScientificName());
	}

}
