/**
 * 
 */
package org.sharks.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.sharks.service.test.util.TestModelUtils.buildCountry;
import static org.sharks.service.test.util.TestModelUtils.buildEntity;
import static org.sharks.service.test.util.TestModelUtils.buildPoA;
import static org.sharks.service.test.util.TestModelUtils.findFirst;
import static org.sharks.service.test.util.TestUtils.getResource;

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
import org.sharks.service.dto.CountryEntity;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.dto.FaoLexDocument;
import org.sharks.service.http.HttpClient;
import org.sharks.service.impl.CountryServiceImpl;
import org.sharks.service.informea.CountryManagmentEntityService;
import org.sharks.service.moniker.MonikerServiceImpl;
import org.sharks.service.moniker.rest.MonikersRestClient;
import org.sharks.service.refpub.RefPubServiceImpl;
import org.sharks.service.refpub.rest.RefPubRestClient;
import org.sharks.service.test.util.NoCache;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ CountryServiceImpl.class, RefPubServiceImpl.class, MonikerServiceImpl.class, NoCache.class })
public class CountryServiceIntegrationTest {

	@Inject
	CountryService service;

	@Produces
	private CountryManagmentEntityService produce() {
		return Mockito.mock(CountryManagmentEntityService.class);
	}

	@Produces
	private MonikersRestClient setUpBMonikersRestClient() throws MalformedURLException {
		HttpClient httpClient = Mockito.mock(HttpClient.class);

		String content = getResource("/monikers/rfb4iso3.xml");
		when(httpClient.get(new URL("http://localhost/rfb4iso3/ALB"))).thenReturn(content);

		content = getResource("/monikers/rfb4iso3_not_found.xml");
		when(httpClient.get(new URL("http://localhost/rfb4iso3/NOT_EXISTS_RFB"))).thenReturn(content);

		content = getResource("/monikers/rfb.xml");
		when(httpClient.get(new URL("http://localhost/rfb/9294"))).thenReturn(content);
		content = getResource("/monikers/rfb2.xml");
		when(httpClient.get(new URL("http://localhost/rfb/22050"))).thenReturn(content);

		content = getResource("/monikers/rfb_not_found.xml");
		when(httpClient.get(new URL("http://localhost/rfb/NOT_EXISTS"))).thenReturn(content);

		content = getResource("/monikers/faolexfi.xml");
		when(httpClient.get(new URL("http://localhost/faolexfi/kwid=089/iso3=ALB"))).thenReturn(content);
		content = getResource("/monikers/faolexfi_not_found.xml");
		when(httpClient.get(new URL("http://localhost/faolexfi/kwid=089/iso3=NOT_EXISTS_RFB"))).thenReturn(content);

		return new MonikersRestClient("http://localhost/", httpClient);
	}

	@Produces
	private RefPubRestClient setUpBeforeClass() throws Exception {
		HttpClient httpClient = Mockito.mock(HttpClient.class);

		String content = getResource("/refpub/country_iso3.xml");
		when(httpClient.get(new URL("http://localhost/concept/Country/codesystem/UN-ISO3/code/ALB/xml")))
				.thenReturn(content);

		content = getResource("/refpub/country_not_found.xml");
		when(httpClient.get(new URL("http://localhost/concept/Country/codesystem/UN-ISO3/code/NOT_EXISTS/xml")))
				.thenReturn(content);

		return new RefPubRestClient("http://localhost/", httpClient);
	}

	@Produces
	private ManagementEntityDao setupCountryDao() {
		ManagementEntityDao dao = Mockito.mock(ManagementEntityDao.class);

		MgmtEntity country = buildCountry("ALB", "Albania", buildPoA(), buildPoA());
		when(dao.getByAcronym("ALB")).thenReturn(country);
		when(dao.getByAcronym("NOT_EXISTS_RFB")).thenReturn(country);

		when(dao.getByAcronym("NOT_EXISTS")).thenReturn(null);

		MgmtEntity country2 = buildCountry("USA", "USA");
		when(dao.listCountries(false, false)).thenReturn(Arrays.asList(country, country2));
		when(dao.listCountries(true, false)).thenReturn(Arrays.asList(country));

		when(dao.getByAcronym("ICCAT")).thenReturn(buildEntity(0, "ICCAT", new InformationSource()));
		when(dao.getByAcronym("SEAFO")).thenReturn(buildEntity(0, "ICCAT"));

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

		CountryEntity iccat = findFirst(country.getRfbs(), (entity) -> entity.getAcronym().equals("ICCAT"));
		assertNotNull(iccat);

		CountryEntity eifaac = findFirst(country.getRfbs(), (entity) -> entity.getAcronym().equals("EIFAAC"));
		assertNotNull(eifaac);

		assertFalse(country.getFaoLexDocuments().isEmpty());

		FaoLexDocument second = country.getFaoLexDocuments().get(1);
		assertNotNull(second.getDateOfOriginalText());

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

		CountryEntry alb = findFirst(countries, (entity) -> entity.getCode().equals("ALB"));
		assertNotNull(alb);
		assertNotNull(alb.getContinent());

		CountryEntry usa = findFirst(countries, (entity) -> entity.getCode().equals("USA"));
		assertNotNull(usa);

		// USA not present in refPub mock
		assertNull(usa.getContinent());
	}

	@Test
	public void testListOnlyWithPoas() {
		List<CountryEntry> countries = service.list(true);
		assertNotNull(countries);
		assertEquals(1, countries.size());

		CountryEntry alb = findFirst(countries, (entity) -> entity.getCode().equals("ALB"));
		assertNotNull(alb);
		assertNotNull(alb.getContinent());
	}

}
