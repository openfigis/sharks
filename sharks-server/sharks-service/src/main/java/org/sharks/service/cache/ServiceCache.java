/**
 * 
 */
package org.sharks.service.cache;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ServiceCache<K,V> {
	
	public CacheElement<V> get(K key);	

	public void put(K key, V value);
	
	public int size();
	
	public void clear();
	
	public void flush();
	
	@Data
	public static class CacheElement<V> {
		
		private final boolean present;
		private final V value;
		
	}

}
