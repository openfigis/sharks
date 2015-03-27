/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CountryService {
	
	List<Country> list();
}
