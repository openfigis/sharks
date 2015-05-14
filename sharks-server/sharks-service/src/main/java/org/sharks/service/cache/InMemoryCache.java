/**
 * 
 */
package org.sharks.service.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class InMemoryCache<K, V> implements Cache<K, V> {
	
	private Map<K, V> cache = new HashMap<K, V>();

	@Override
	public boolean contains(K key) {
		return cache.containsKey(key);
	}

	@Override
	public V get(K key) {
		return cache.get(key);
	}

	@Override
	public void put(K key, V value) {
		cache.put(key, value);
	}

	@Override
	public int size() {
		return cache.size();
	}

}
