/**
 * 
 */
package org.sharks.service.cache;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface Cache<K,V> {
	
	public boolean contains(K key);
	
	public V get(K key);	

	public void put(K key, V value);
	
	public int size();

}
