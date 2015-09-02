/**
 * 
 */
package org.sharks.service.geoserver;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
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
@Singleton 
@Service(name="geoserver", type=ServiceType.EXTERNAL)
public class GeoServerServiceImpl implements GeoServerService {
	
	private static final String CACHE_KEY = "list";
	
	@Inject @CacheName("specieslist")
	private ServiceCache<String, Set<String>> speciesListCache;
	
	@Inject
	private GeoServerRestClient client;

	@Override
	public boolean hasSpeciesDistributionMap(String alpha3code) {
		Set<String> speciesList = getSpeciesList();
		return speciesList.contains(alpha3code);
	}
	
	private Set<String> getSpeciesList() {
		
		CacheElement<Set<String>> element = speciesListCache.get(CACHE_KEY);
		if (element.isPresent()) return element.getValue();
		
		SpeciesList list = getList();
		
		if (list!=null) {
			
			Set<String> species = new HashSet<String>();
			for (SpeciesItem item:list.getItems()) species.add(item.getAlphaCode());
			speciesListCache.put(CACHE_KEY, species);
			return species;
			
		} else return Collections.emptySet();
	}

	private SpeciesList getList() {
		log.trace("getting species list");
		
		try {
			SpeciesList list = client.getSpeciesList();
			return list;
		} catch(Exception e) {
			log.error("Failed loading the species list", e);
			return null;
		}
	}
}
