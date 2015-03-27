/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.PoADetails;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface PoAService {

	List<PoADetails> poasForCountry(String countryCode);

	List<PoADetails> list();
	
}
