/**
 * 
 */
package org.sharks.service.refpub;

import javax.inject.Inject;

import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class RefPubServiceImpl implements RefPubService {
	
	@Inject
	private RefPubRestClient restClient;
	
	@Inject
	private RefPubCache<RefPubCountry> countryCache;
	
	@Inject
	private RefPubCache<RefPubSpecies> speciesCache;

	@Override
	public RefPubCountry getCountry(String iso3Code) {
		if (countryCache.contains(iso3Code)) return countryCache.get(iso3Code);
		
		RefPubCountry country = restClient.getCountry(iso3Code);
		countryCache.put(iso3Code, country);
		return country;
	}

	@Override
	public RefPubSpecies getSpecies(String alpha3Code) {
		if (speciesCache.contains(alpha3Code)) return speciesCache.get(alpha3Code);
		
		RefPubSpecies species = restClient.getSpecies(alpha3Code);
		speciesCache.put(alpha3Code, species);
		return species;
	}
}
