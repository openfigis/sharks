/**
 * 
 */
package org.sharks.service.refpub;

import java.util.List;
import java.util.Map;

import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface RefPubService {

	RefPubCountry getCountry(String iso3Code);
	
	Map<String,RefPubCountry> getCountries(List<String> iso3Codes);

	RefPubSpecies getSpecies(String alpha3Code);
}
