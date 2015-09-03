/**
 * 
 */
package org.sharks.service.kor;

import org.sharks.service.kor.dto.KorResource;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface KorService {
	
	public KorResource getResource(String acronym);

}
