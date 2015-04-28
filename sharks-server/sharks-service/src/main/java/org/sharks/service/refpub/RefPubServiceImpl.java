/**
 * 
 */
package org.sharks.service.refpub;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class RefPubServiceImpl implements RefPubService {
	
	@Inject
	private RefPubRestClient restClient;
	
	@Inject
	private RefPubCache cache;

	@Override
	public RefPubCountry getCountry(String iso3Code) {
		if (cache.contains(iso3Code)) return cache.get(iso3Code);
		
		RefPubCountry country = restClient.getCountry(iso3Code);
		cache.put(country);
		return country;
	}

	@Override
	public Map<String, RefPubCountry> getCountries(List<String> iso3Codes) {
		return iso3Codes.stream().collect(Collectors.toMap(Function.identity(), code->getCountry(code)));
	}

}
