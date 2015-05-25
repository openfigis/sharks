/**
 * 
 */
package org.sharks.service.geoserver;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.cache.CacheName;
import org.sharks.service.cache.ServiceCache;
import org.sharks.service.cache.ServiceCache.CacheElement;
import org.sharks.service.geoserver.dto.SpeciesItem;
import org.sharks.service.geoserver.dto.SpeciesList;
import org.sharks.service.geoserver.rest.GeoServerRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class GeoServerServiceImpl implements GeoServerService {
	
	private boolean chacheLoaded = false;
	
	@Inject @CacheName("specieslist")
	private ServiceCache<String, Boolean> speciesList;
	
	@Inject
	private GeoServerRestClient client;

	@Override
	public boolean hasSpeciesDistributionMap(String alpha3code) {
		checkCache();
		CacheElement<Boolean> element = speciesList.get(alpha3code);
		return element.isPresent() && element.getValue();
	}
	
	private void checkCache() {
		if (!chacheLoaded) loadCache();
	}

	private void loadCache() {
		log.trace("loading species list cache");
		
		try {
			SpeciesList list = client.getSpeciesList();
			for (SpeciesItem item:list.getItems()) {
				speciesList.put(item.getAlphaCode(), true);
			}
		} catch(Exception e) {
			log.error("Failed loading the species list", e);
		} finally {
			chacheLoaded = true;
		}
	}
}
