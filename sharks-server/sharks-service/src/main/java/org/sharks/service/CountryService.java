/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.CountryDetails;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CountryService {
	
	List<CountryDetails> list();
}
