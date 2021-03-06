/**
 * 
 */
package org.sharks.service.producer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.sharks.service.test.util.TestModelUtils.aRefPubCountry;
import static org.sharks.service.test.util.TestModelUtils.buildCountry;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
public class CountryEntryProducerTest {
	
	@Inject
	CountryEntryProducer producer;
	
	RefPubCountry aCountry;
	
	@Produces
	protected RefPubService setupRefPubService() {
		RefPubService service = Mockito.mock(RefPubService.class);
		aCountry = aRefPubCountry();
		when(service.getCountryByIso3("AFG")).thenReturn(aCountry);
		return service;
	}

	/**
	 * Checks if the data is substituted with the one from the ref pub service.
	 */
	@Test
	public void testProduceUsingRefPubService() {
		MgmtEntity country = buildCountry("AFG", "Afghanistan");
		
		CountryEntry entry = producer.produce(country);
		assertNotNull(entry);
		assertEquals("AFG", entry.getCode());
		
		//the continent has been set with the one from ref pub service
		assertNotNull(entry.getContinent());
		assertEquals(aCountry.getContinent(), entry.getContinent());
	}
	
	/**
	 * Checks if the data is not changed if is not present in the ref pub service.
	 */
	@Test
	public void testProduceWithouthRefPubService() {
		MgmtEntity country = buildCountry("NOT_EXISTS", "Afghanistan");
		
		CountryEntry entry = producer.produce(country);
		assertNotNull(entry);
		assertEquals("NOT_EXISTS", entry.getCode());
		
		//the continent has been not set
		assertNull(entry.getContinent());
	}

}
