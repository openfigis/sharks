/**
 * 
 */
package org.sharks.service.refpub;

import org.sharks.service.refpub.dto.RefPubCountry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface RefPubCache {
	
	public boolean contains(String iso3Code);
	
	public RefPubCountry get(String iso3Code);	

	public void put(RefPubCountry country);
	
	public int size();

}
