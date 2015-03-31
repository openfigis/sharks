/**
 * 
 */
package org.sharks.service.refpub;

import java.util.HashMap;
import java.util.Map;

import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubCountry.Code;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class DefaultRefPubCache implements RefPubCache {
	
	private Map<String, RefPubCountry> cache = new HashMap<String, RefPubCountry>();
	
	@Override
	public boolean contains(String iso3Code) {
		return cache.containsKey(iso3Code);
	}

	@Override
	public RefPubCountry get(String iso3Code) {
		return cache.get(iso3Code);
	}

	@Override
	public void put(RefPubCountry country) {
		Code code = country.getUnIso3Code();
		if (code == null) throw new IllegalArgumentException("Missing UN-ISO3 code in country "+country);
		cache.put(code.getConcept(), country);
	}

	@Override
	public int size() {
		return cache.size();
	}


}
