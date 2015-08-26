package org.sharks.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.sharks.service.test.util.TestModelUtils.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.dto.SpeciesDetails;
import org.sharks.service.dto.SpeciesEntry;
import org.sharks.service.geoserver.GeoServerService;
import org.sharks.service.impl.SpeciesServiceImpl;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.storage.dao.SpeciesDao;
import org.sharks.storage.domain.CustomSpeciesGrp;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({SpeciesServiceImpl.class})
public class SpeciesServiceTest {

	@Inject
	SpeciesService service;
	
	RefPubSpecies aRefPubSpecies;
	Species noRefPubSpecies;
	
	@Produces
	protected SpeciesDao setupSpeciesDao() {
		SpeciesDao dao = Mockito.mock(SpeciesDao.class);
		
		Species aSpecies = buildSpecies("ALV", "Alopias vulpinus");
		aSpecies.setMeasures(Collections.singletonList(buildMeasure(0, "a")));
		
		CustomSpeciesGrp group = new CustomSpeciesGrp();
		group.setMeasures(Arrays.asList(buildMeasure(0, "a"), buildMeasure(1, "b")));
		aSpecies.setCustomSpeciesGrps(Collections.singletonList(group));
		
		when(dao.getByAlphaCode("ALV")).thenReturn(aSpecies);
		when(dao.getByAlphaCode("NOMAP")).thenReturn(aSpecies);

		noRefPubSpecies = buildSpecies("NRF", "Alopias vulpinus");
		when(dao.getByAlphaCode("NRF")).thenReturn(noRefPubSpecies);
		
		when(dao.list()).thenReturn(Arrays.asList(aSpecies, noRefPubSpecies));
		
		when(dao.listWithMeasures()).thenReturn(Arrays.asList(aSpecies));
		
		return dao;
	}
	
	@Produces
	protected RefPubService setupRefPubService() {
		RefPubService service = Mockito.mock(RefPubService.class);
		aRefPubSpecies = aRefPubSpecies();
		when(service.getSpecies("ALV")).thenReturn(aRefPubSpecies);
		return service;
	}
	
	@Produces
	private GeoServerService setupGeoServerService() {
		GeoServerService service = Mockito.mock(GeoServerService.class);
		
		when(service.hasSpeciesDistributionMap("ALV")).thenReturn(true);
		when(service.hasSpeciesDistributionMap("NOMAP")).thenReturn(false);
		
		return service;
	}
	

	/**
	 * Test the correct path: information from the storage are integrated with the one from refPub service.
	 */
	@Test
	public void testGetSpecies() {
		SpeciesDetails details = service.getSpecies("ALV");
		
		assertNotNull(details);
		assertEquals("ALV", details.getAlphaCode());
		
		assertEquals(aRefPubSpecies.getLongNames().getFrench(), details.getOfficialNames().get("fr"));
		
		assertEquals(2, details.getMeasures().size());
		
		assertTrue(details.isHasDistributionMap());
	}
	
	/**
	 * Test for a species from the storage but not present in refPub service.
	 */
	@Test
	public void testGetSpeciesNoRefPub() {
		SpeciesDetails details = service.getSpecies("NRF");
		
		assertNotNull(details);
		assertEquals("NRF", details.getAlphaCode());
		
		assertEquals(noRefPubSpecies.getScientificName(), details.getScientificName());
	}
	
	@Test
	public void testGetSpeciesNoMap() {
		SpeciesDetails details = service.getSpecies("NOMAP");
		
		assertNotNull(details);
		assertEquals("NOMAP", details.getAlphaCode());
		
		assertFalse(details.isHasDistributionMap());
	}
	
	/**
	 * Test for wrong species code
	 */
	@Test
	public void testGetSpeciesWrongAlphaCode() {
		SpeciesDetails details = service.getSpecies("NOT_EXISTS");
		
		assertNull(details);
	}
	
	@Test
	public void testListAll() {
		List<SpeciesEntry> entries = service.list(false);
		
		assertEquals(2, entries.size());
		
		SpeciesEntry alv = findFirst(entries, species->species.getAlphaCode().equals("ALV"));
		assertEquals(aRefPubSpecies.getLongNames().getEnglish(), alv.getEnglishName());
		
		SpeciesEntry nrf = findFirst(entries, species->species.getAlphaCode().equals("NRF"));
		assertNull(nrf.getEnglishName());
	}
	
	@Test
	public void testListOnlyWithMeasures() {
		List<SpeciesEntry> entries = service.list(true);
		
		assertEquals(1, entries.size());
		
		SpeciesEntry alv = findFirst(entries, species->species.getAlphaCode().equals("ALV"));
		assertNotNull(alv);
		
		SpeciesEntry nrf = findFirst(entries, species->species.getAlphaCode().equals("NRF"));
		assertNull(nrf);
	}


}
