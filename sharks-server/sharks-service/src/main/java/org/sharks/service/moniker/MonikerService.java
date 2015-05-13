/**
 * 
 */
package org.sharks.service.moniker;

import java.util.List;

import org.sharks.service.moniker.dto.RfbEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface MonikerService {
	
	public List<RfbEntry> getRfbs(String countryIso3);

}
