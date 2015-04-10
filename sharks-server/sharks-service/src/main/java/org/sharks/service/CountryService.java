/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.CountryEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CountryService {
	
	List<CountryEntry> list(boolean onyWithPoas);
}
