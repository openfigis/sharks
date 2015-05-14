/**
 * 
 */
package org.sharks.service.util;

import org.jboss.weld.exceptions.IllegalStateException;
import org.sharks.service.cache.Cache;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class NoCache<K, V> implements Cache<K, V> {

	@Override
	public boolean contains(K key) {
		return false;
	}

	@Override
	public V get(K key) {
		throw new IllegalStateException("Get not allowed");
	}

	@Override
	public void put(K key, V value) {
	}

	@Override
	public int size() {
		return 0;
	}

}
