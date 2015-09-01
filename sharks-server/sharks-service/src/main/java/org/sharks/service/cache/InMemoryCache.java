/**
 * 
 */
package org.sharks.service.cache;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Alternative
public class InMemoryCache<K, V> implements ServiceCache<K, V> {
	
	private Map<K, V> cache = new HashMap<K, V>();

	@Override
	public CacheElement<V> get(K key) {
		return new CacheElement<V>(cache.containsKey(key), cache.get(key));
	}

	@Override
	public void put(K key, V value) {
		cache.put(key, value);
	}

	@Override
	public int size() {
		return cache.size();
	}

	@Override
	public void clear() {
		cache.clear();
	}

	@Override
	public void flush() {
	}

}
