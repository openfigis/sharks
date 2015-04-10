/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.PoAEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface PoAService {

	List<PoAEntry> poasForCountry(String countryCode);

	List<PoAEntry> list();
	
}
