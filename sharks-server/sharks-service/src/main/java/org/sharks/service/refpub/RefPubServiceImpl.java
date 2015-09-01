/**
 * 
 */
package org.sharks.service.refpub;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.ServiceCache;
import org.sharks.service.cache.CacheName;
import org.sharks.service.cache.ServiceCache.CacheElement;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * This client has been created as replacement of refpub client 
 * currently not available in a public repository.
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Slf4j 
@Singleton
@Service(name="refpub",type=ServiceType.EXTERNAL)
public class RefPubServiceImpl implements RefPubService {

	@Inject
	private RefPubRestClient restClient;

	@Inject @CacheName("countryIso3")
	private ServiceCache<String, RefPubCountry> iso3CountriesCache;
	
	@Inject @CacheName("countryIso2")
	private ServiceCache<String, RefPubCountry> iso2CountriesCache;
	
	@Inject @CacheName("species")
	private ServiceCache<String, RefPubSpecies> speciesCache;

	@Override
	public RefPubCountry getCountryByIso3(String iso3Code) {
		CacheElement<RefPubCountry> cacheElement = iso3CountriesCache.get(iso3Code);
		if (cacheElement.isPresent()) return cacheElement.getValue();
		
		try {
			RefPubCountry country = restClient.getCountryByIso3(iso3Code);
			
			iso3CountriesCache.put(iso3Code, country);
			
			return country;
		} catch(Exception e) {
			log.error("Error getting country "+iso3Code+" from refPub", e);
			return null;
		}
	}
	
	@Override
	public RefPubCountry getCountryByIso2(String iso2Code) {
		CacheElement<RefPubCountry> cacheElement = iso2CountriesCache.get(iso2Code);
		if (cacheElement.isPresent()) return cacheElement.getValue();
		
		try {
			RefPubCountry country = restClient.getCountryByIso2(iso2Code);
			
			iso2CountriesCache.put(iso2Code, country);
			
			return country;
		} catch(Exception e) {
			log.error("Error getting country "+iso2Code+" from refPub", e);
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
