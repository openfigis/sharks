/**
 * 
 */
package org.sharks.service.cache;

import javax.enterprise.inject.Alternative;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Alternative
public class EhServiceCache<K, V> implements ServiceCache<K, V> {
	
	private Cache cache;
	
	public EhServiceCache(Cache cache) {
		this.cache = cache;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CacheElement<V> get(K key) {
		Element element = cache.get(key);
		if (element == null) return new CacheElement<V>(false, null);
		return new CacheElement<V>(true, (V) element.getObjectValue());
	}

	@Override
	public void put(K key, V value) {
		cache.put(new Element(key, value));
	}

	@Override
	public int size() {
		return cache.getSize();
	}

}
