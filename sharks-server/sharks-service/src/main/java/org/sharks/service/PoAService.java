/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.PoAEntry;
import org.sharks.storage.domain.PoA;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface PoAService {

	List<PoAEntry> poasForCountry(String countryCode);

	List<PoAEntry> list();
	
	PoA get(Long code);
	
}
