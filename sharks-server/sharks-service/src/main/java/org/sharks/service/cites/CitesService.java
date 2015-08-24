/**
 * 
 */
package org.sharks.service.cites;

import java.util.List;

import org.sharks.service.cites.dto.CitesCountry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CitesService {
	
	public List<CitesCountry> getParties();
}
