/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.CountryDetails;
import org.sharks.service.dto.CountryEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CountryService {
	
	CountryDetails get(String code);
	
	List<CountryEntry> list(boolean onyWithPoas);
	
}
