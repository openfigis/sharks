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

	RefPubCountry getCountry(String iso3Code);
	
	RefPubSpecies getSpecies(String alpha3Code);
}
