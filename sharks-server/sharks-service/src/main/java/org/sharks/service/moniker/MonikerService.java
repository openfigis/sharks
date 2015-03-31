/**
 * 
 */
package org.sharks.service.moniker;

import java.util.List;

import org.sharks.service.moniker.dto.Rfb;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface MonikerService {
	
	public List<Rfb> getRfbs(String countryIso3);

}
