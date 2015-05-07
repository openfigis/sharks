/**
 * 
 */
package org.sharks.service.refpub;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface RefPubCache<T> {
	
	public boolean contains(String code);
	
	public T get(String code);	

	public void put(String code, T item);
	
	public int size();

}
