/**
 * 
 */
package org.sharks.service.refpub;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.cache.ServiceCache;
import org.sharks.service.cache.CacheName;
import org.sharks.service.cache.ServiceCache.CacheElement;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class RefPubServiceImpl implements RefPubService {

	@Inject
	private RefPubRestClient restClient;

	@Inject @CacheName("country")
	private ServiceCache<String, RefPubCountry> countriesCache;
	
	@Inject @CacheName("species")
	private ServiceCache<String, RefPubSpecies> speciesCache;

	@Override
	public RefPubCountry getCountry(String iso3Code) {
		CacheElement<RefPubCountry> cacheElement = countriesCache.get(iso3Code);
		if (cacheElement.isPresent()) return cacheElement.getValue();
		
		try {
			RefPubCountry country = restClient.getCountry(iso3Code);
			
			countriesCache.put(iso3Code, country);
			
			return country;
		} catch(Exception e) {
			log.error("Error getting country "+iso3Code+" from refPub", e);
			return null;
		}
	}

	@Override
	public RefPubSpecies getSpecies(String alpha3Code) {
		CacheElement<RefPubSpecies> cacheElement = speciesCache.get(alpha3Code);
		if (cacheElement.isPresent()) return cacheElement.getValue();
		
		try {
			RefPubSpecies species = restClient.getSpecies(alpha3Code);
			
			speciesCache.put(alpha3Code, species);
			
			return species;
		} catch(Exception e) {
			log.error("Error getting species "+alpha3Code+" from refPub", e);
			return null;
		}
	}
}
