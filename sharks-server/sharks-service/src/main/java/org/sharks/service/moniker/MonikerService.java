/**
 * 
 */
package org.sharks.service.moniker;

import java.util.List;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface MonikerService {
	
	/**
	 * Returns a list of RFBs acronym for the specified country.
	 * @param countryIso3 the country ISO3 code.
	 * @return the list of RFB, an empty list if the country has not been found or the list of rfb is not available.
	 */
	public List<String> getRfbsForCountry(String countryIso3);

}
