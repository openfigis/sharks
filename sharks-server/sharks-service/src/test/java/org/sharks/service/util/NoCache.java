/**
 * 
 */
package org.sharks.service.util;

import org.sharks.service.cache.ServiceCache;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class NoCache<K, V> implements ServiceCache<K, V> {

	@Override
	public CacheElement<V> get(K key) {
		return new CacheElement<V>(false, null);
	}

	@Override
	public void put(K key, V value) {
	}

	@Override
	public int size() {
		return 0;
	}

}
