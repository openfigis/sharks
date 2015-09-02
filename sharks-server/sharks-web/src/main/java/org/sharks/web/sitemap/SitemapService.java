/**
 * 
 */
package org.sharks.web.sitemap;

import javax.inject.Inject;

import org.sharks.service.CountryService;
import org.sharks.service.GroupService;
import org.sharks.service.ManagementEntityService;
import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.SpeciesService;
import org.sharks.service.cache.CacheName;
import org.sharks.service.cache.ServiceCache;
import org.sharks.service.cache.ServiceCache.CacheElement;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.dto.GroupEntry;
import org.sharks.service.dto.SpeciesEntry;

import lombok.extern.slf4j.Slf4j;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@Service(name="sitemap",type=ServiceType.INTERNAL)
public class SitemapService {
	
	private static final String CACHE_KEY = "sitemap";
	
	@Inject
	private SitemapBuilder builder;
	
	@Inject
	private SitemapXmlGenerator generator;
	
	@Inject @CacheName("map")
	private ServiceCache<String, String> cache;
	
	@Inject
	private CountryService countryService;
	
	@Inject
	private ManagementEntityService entityService;
	
	@Inject
	private SpeciesService speciesService;
	
	@Inject
	private GroupService groupService;
	
	public String getSitemap() {
		
		CacheElement<String> element = cache.get(CACHE_KEY);
		
		if (element.isPresent()) return element.getValue();
		
		String sitemap = generateSitemap();
		cache.put(CACHE_KEY, sitemap);
		
		return sitemap;
	}
	
	private String generateSitemap() {
		log.trace("generating sitemap");
		
		Sitemap map = buildSitemap();
		String xml = generator.toXml(map);
		
		log.trace("sitemap generation complete");
		
		return xml;
	}
	
	private Sitemap buildSitemap() {
		builder.clean();
		
		builder.addHomePage();
		builder.addSelectionPages();
		
		for (CountryEntry country:countryService.list(true)) builder.addCountryPage(country);
		for (EntityEntry entity:entityService.list(true)) builder.addEntityPage(entity);
		for (SpeciesEntry species:speciesService.list(true)) builder.addSpeciesPage(species);
		for (GroupEntry group:groupService.list()) builder.addGroupPage(group);
		
		return builder.build();
	}
}
