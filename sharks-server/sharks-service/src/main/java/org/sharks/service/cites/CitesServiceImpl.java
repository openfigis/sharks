/**
 * 
 */
package org.sharks.service.cites;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.CacheName;
import org.sharks.service.cache.ServiceCache;
import org.sharks.service.cache.ServiceCache.CacheElement;
import org.sharks.service.cites.dto.CitesCountry;
import org.sharks.service.cites.dto.CitesParties;
import org.sharks.service.cites.rest.CitesRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@Singleton 
@Service(name="cites",type=ServiceType.EXTERNAL)
public class CitesServiceImpl implements CitesService {
	
	private final static String CACHE_KEY = "parties";
	
	@Inject
	private CitesRestClient restClient;
	
	@Inject @CacheName("parties")
	private ServiceCache<String, CitesParties> cache;

	@Override
	public List<CitesCountry> getParties() {
		
		CacheElement<CitesParties> element = cache.get(CACHE_KEY);
		if (element.isPresent()) return element.getValue().getCountries();
		
		CitesParties parties = getCitesParties();
		
		if (parties==null) return Collections.emptyList(); 
			
		cache.put(CACHE_KEY, parties);
		
		return parties.getCountries();
	}
	
	private CitesParties getCitesParties() {
		try {
			return restClient.getParties();
		} catch(Exception e) {
			log.error("Failed retrieving cites parties", e);
			return null;
		}
	}

}
