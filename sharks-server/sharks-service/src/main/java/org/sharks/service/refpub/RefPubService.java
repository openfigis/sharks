/**
 * 
 */
package org.sharks.service.refpub;

import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface RefPubService {

	/**
	 * Returns the {@link RefPubCountry} with the specified ISO3 code.
	 * @param iso3Code the country code.
	 * @return the found {@link RefPubCountry} or <code>null</code> if not found.
	 */
	RefPubCountry getCountryByIso3(String iso3Code);
	
	/**
	 * Returns the {@link RefPubSpecies} with the specified Alpha3 code.
	 * @param alpha3Code the species code.
	 * @return the found {@link RefPubSpecies} or <code>null</code> if not found.
	 */
	RefPubSpecies getSpecies(String alpha3Code);
}
