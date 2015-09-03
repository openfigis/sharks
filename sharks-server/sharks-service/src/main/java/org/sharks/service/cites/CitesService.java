/**
 * 
 */
package org.sharks.service.cites;

import java.util.List;

import org.sharks.service.informea.dto.InformeaCountry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CitesService {
	
	public List<InformeaCountry> getParties();
}
