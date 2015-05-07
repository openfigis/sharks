/**
 * 
 */
package org.sharks.service.refpub;

import java.util.HashMap;
import java.util.Map;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class DefaultRefPubCache<T> implements RefPubCache<T> {
	
	private Map<String, T> cache = new HashMap<String, T>();
	
	@Override
	public boolean contains(String iso3Code) {
		return cache.containsKey(iso3Code);
	}

	@Override
	public T get(String iso3Code) {
		return cache.get(iso3Code);
	}

	@Override
	public void put(String code, T item) {
			cache.put(code, item);
	}

	@Override
	public int size() {
		return cache.size();
	}


}
