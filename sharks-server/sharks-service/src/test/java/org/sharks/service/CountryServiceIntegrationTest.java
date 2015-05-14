/**
 * 
 */
package org.sharks.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestModelUtils.builEntity;
import static org.sharks.service.util.TestModelUtils.buildCountry;
import static org.sharks.service.util.TestModelUtils.findFirst;
import static org.sharks.service.util.TestUtils.getResource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.dto.CountryDetails;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.http.HttpClient;
import org.sharks.service.impl.CountryServiceImpl;
import org.sharks.service.moniker.MonikerServiceImpl;
import org.sharks.service.moniker.rest.MonikersRestClient;
import org.sharks.service.refpub.DefaultRefPubCache;
import org.sharks.service.refpub.RefPubServiceImpl;
import org.sharks.service.refpub.rest.RefPubRestClient;
import org.sharks.service.util.NoCache;
import org.sharks.storage.dao.CountryDao;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.Country;
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.PoA;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({CountryServiceImpl.class, RefPubServiceImpl.class, MonikerServiceImpl.class, DefaultRefPubCache.class, NoCache.class})
public class CountryServiceIntegrationTest {
	
	@Inject
	CountryService service;
	
	@Produces
	private MonikersRestClient setUpBMonikersRestClient() throws MalformedURLException {
		HttpClient httpClient = Mockito.mock(HttpClient.class);
		
		String content = getResource("/rfb4iso3.xml");
		when(httpClient.get(new URL("http://localhost/rfb4iso3/ALB"))).thenReturn(content);
		
		content = getResource("/rfb4iso3_not_found.xml");
		when(httpClient.get(new URL("http://localhost/rfb4iso3/NOT_EXISTS_RFB"))).thenReturn(content);
		
		when(httpClient.get(new URL("http://localhost/rfb4iso3/ERROR"))).thenThrow(new RuntimeException("Get failed"));
		
		
		content = getResource("/figisdoc.xml");
		when(httpClient.get(new URL("http://localhost/figisdoc/organization/9294"))).thenReturn(content);
		content = getResource("/figisdoc2.xml");
		when(httpClient.get(new URL("http://localhost/figisdoc/organization/22050"))).thenReturn(content);
		
		content = getResource("/figisdoc_not_found.xml");
		when(httpClient.get(new URL("http://localhost/figisdoc/organization/NOT_EXISTS"))).thenReturn(content);
		
		when(httpClient.get(new URL("http://localhost/figisdoc/organization/ERROR"))).thenThrow(new RuntimeException("Get failed"));

		
		return new MonikersRestClient("http://localhost/", httpClient);
	}
	
	@Produces
	private RefPubRestClient setUpBeforeClass() throws Exception {
		HttpClient httpClient = Mockito.mock(HttpClient.class);
		
		String content = getResource("/country.xml");
		when(httpClient.get(new URL("http://localhost/concept/Country/codesystem/UN-ISO3/code/ALB/xml"))).thenReturn(content);
		
		content = getResource("/country_not_found.xml");
		when(httpClient.get(new URL("http://localhost/concept/Country/codesystem/UN-ISO3/code/NOT_EXISTS/xml"))).thenReturn(content);
		
		when(httpClient.get(new URL("http://localhost/concept/Country/codesystem/UN-ISO3/code/ERROR/xml")))
		.thenThrow(new RuntimeException("Get failed"));
		
		return new RefPubRestClient("http://localhost/", httpClient);
	}

	@Produces
	private CountryDao setupCountryDao() {
		CountryDao dao = Mockito.mock(CountryDao.class);
		
		Country country = buildCountry("ALB", "Albania", new PoA(), new PoA());
		when(dao.get("ALB")).thenReturn(country);
		when(dao.get("NOT_EXISTS_RFB")).thenReturn(country);
		
		when(dao.get("NOT_EXISTS")).thenReturn(null);
		
		Country country2 = buildCountry("USA", "USA", new PoA());
		when(dao.list()).thenReturn(Arrays.asList(country, country2));
		when(dao.listWithPoAs()).thenReturn(Arrays.asList(country));
		
		return dao;
	}
	
	@Produces
	private ManagementEntityDao setupManagementEntityDao() {
		ManagementEntityDao dao = Mockito.mock(ManagementEntityDao.class);
		
		MgmtEntity entity = builEntity(0,"ICCAT");
		when(dao.getByAcronym("ICCAT")).thenReturn(entity);
		
		return dao;
	}

	
	/**
	 * Test method for {@link org.sharks.service.impl.CountryServiceImpl#get(java.lang.String)}.
	 */
	@Test
	public void testGet() {
		CountryDetails country = service.get("ALB");
		assertNotNull(country);
		
		assertEquals("ALB", country.getCode());
		assertEquals(2, country.getPoas().size());
		
		assertEquals(2, country.getRfbs().size());
		
		EntityEntry iccat = findFirst(country.getRfbs(), (entity)->entity.getAcronym().equals("ICCAT"));
		assertNotNull(iccat);
		
		EntityEntry eifaac = findFirst(country.getRfbs(), (entity)->entity.getAcronym().equals("EIFAAC"));
		assertNotNull(eifaac);
	}
	
	@Test
	public void testGetMissingCountry() {
		CountryDetails country = service.get("NOT_EXISTS");
		assertNull(country);
	}
	
	@Test
	public void testGetMissingRbs() {
		CountryDetails country = service.get("NOT_EXISTS_RFB");
		assertNotNull(country);
		
		assertEquals("ALB", country.getCode());
		assertEquals(2, country.getPoas().size());
		
		assertEquals(0, country.getRfbs().size());
	}

	/**
	 * Test method for {@link org.sharks.service.impl.CountryServiceImpl#list(boolean)}.
	 */
	@Test
	public void testListAll() {
		List<CountryEntry> countries = service.list(false);
		assertNotNull(countries);
		assertEquals(2, countries.size());
		
		CountryEntry alb = findFirst(countries, (entity)->entity.getCode().equals("ALB"));
		assertNotNull(alb);
		assertNotNull(alb.getContinent());
		
		CountryEntry usa = findFirst(countries, (entity)->entity.getCode().equals("USA"));
		assertNotNull(usa);
		
		//USA not present in refPub mock
		assertNull(usa.getContinent());
	}
	
	@Test
	public void testListOnlyWithPoas() {
		List<CountryEntry> countries = service.list(true);
		assertNotNull(countries);
		assertEquals(1, countries.size());
		
		CountryEntry alb = findFirst(countries, (entity)->entity.getCode().equals("ALB"));
		assertNotNull(alb);
		assertNotNull(alb.getContinent());
	}

}
