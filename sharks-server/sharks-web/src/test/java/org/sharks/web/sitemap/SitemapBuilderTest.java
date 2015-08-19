/**
 * 
 */
package org.sharks.web.sitemap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.sharks.config.Configuration;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.dto.SpeciesEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SitemapBuilderTest {
	
	private SitemapBuilder builder;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Configuration configuration = mock(Configuration.class);
		when(configuration.getSharksClientUrl()).thenReturn(new URL("http://www.example.com"));
		builder = new SitemapBuilder(configuration);
	}

	/**
	 * Test method for {@link org.sharks.web.sitemap.SitemapBuilder#addSelectionPages()}.
	 */
	@Test
	public void testAddSelectionPages() {
		builder.addSelectionPages();
		SiteMap map = builder.build();
		assertEquals(3, map.getUrls().size());
	}

	/**
	 * Test method for {@link org.sharks.web.sitemap.SitemapBuilder#addSpeciesPage(org.sharks.service.dto.SpeciesEntry)}.
	 * @throws MalformedURLException 
	 */
	@Test
	public void testAddSpeciesPage() throws MalformedURLException {
		SpeciesEntry entry = new SpeciesEntry("ABC", null, null, false);
		
		builder.addSpeciesPage(entry);
		SiteMap map = builder.build();
		
		assertEquals(1, map.getUrls().size());
		URL url = map.getUrls().get(0);
		assertEquals(new URL("http://www.example.com/species/ABC"), url);
	}

	/**
	 * Test method for {@link org.sharks.web.sitemap.SitemapBuilder#addEntityPage(org.sharks.service.dto.EntityEntry)}.
	 * @throws MalformedURLException 
	 */
	@Test
	public void testAddEntityPage() throws MalformedURLException {
		EntityEntry entry = new EntityEntry("ABC", null);
		
		builder.addEntityPage(entry);
		SiteMap map = builder.build();
		
		assertEquals(1, map.getUrls().size());
		URL url = map.getUrls().get(0);
		assertEquals(new URL("http://www.example.com/institution/ABC"), url);
	}

	/**
	 * Test method for {@link org.sharks.web.sitemap.SitemapBuilder#addCountryPage(org.sharks.service.dto.CountryEntity)}.
	 * @throws MalformedURLException 
	 */
	@Test
	public void testAddCountryPage() throws MalformedURLException {
		CountryEntry entry = new CountryEntry("ABC", null, null);
		
		builder.addCountryPage(entry);
		SiteMap map = builder.build();
		
		assertEquals(1, map.getUrls().size());
		URL url = map.getUrls().get(0);
		assertEquals(new URL("http://www.example.com/country/ABC"), url);
	}

}
