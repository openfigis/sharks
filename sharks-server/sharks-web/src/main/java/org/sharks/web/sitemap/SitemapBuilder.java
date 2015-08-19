/**
 * 
 */
package org.sharks.web.sitemap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jboss.weld.exceptions.IllegalStateException;
import org.sharks.config.Configuration;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.dto.SpeciesEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SitemapBuilder {
	
	private static final String SPECIES_PATH = "species";
	private static final String SPECIES_SELECTION_PATH = "species";
	
	private static final String ENTITY_PATH = "institution";
	private static final String ENTITY_SELECTION_PATH = "institutions";
	
	private static final String COUNTRY_PATH = "country";
	private static final String COUNTRY_SELECTION_PATH = "countries";
	
	private URL baseUrl;
	private List<URL> urls;
	
	public SitemapBuilder(Configuration configuration) {
		baseUrl = configuration.getSharksClientUrl();
		urls = new ArrayList<URL>();
	}
	
	public void addSelectionPages() {
		addSelectionPage(SPECIES_SELECTION_PATH);
		addSelectionPage(ENTITY_SELECTION_PATH);
		addSelectionPage(COUNTRY_SELECTION_PATH);
	}
	
	private void addSelectionPage(String selectionPath) {
		URL selectionPageUrl = getPageUrl(selectionPath);
		addUrl(selectionPageUrl);
	}
	
	public void addSpeciesPage(SpeciesEntry entry) {
		addEntryPage(SPECIES_PATH, entry.getAlphaCode());
	}
	
	public void addEntityPage(EntityEntry entry) {
		addEntryPage(ENTITY_PATH, entry.getAcronym());
	}
	
	public void addCountryPage(CountryEntry entry) {
		addEntryPage(COUNTRY_PATH, entry.getCode());
	}
	
	private void addEntryPage(String basePath, String code) {
		String entryPath = basePath+"/"+code;
		URL entryPageUrl = getPageUrl(entryPath);
		addUrl(entryPageUrl);
	}
	
	private void addUrl(URL url) {
		urls.add(url);
	}
	
	private URL getPageUrl(String path) {
		try {
			return new URL(baseUrl, path);
		} catch (MalformedURLException e) {
			throw new IllegalStateException("page url generation failed path: "+path, e);
		}
	}
	
	public SiteMap build() {
		return new SiteMap(baseUrl, urls);
	}

}
