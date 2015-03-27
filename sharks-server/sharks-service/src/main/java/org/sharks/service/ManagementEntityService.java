/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.EntityDetails;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ManagementEntityService {
	
	List<EntityDetails> list();
	
	List<Country> getCountries(String acronym);
}
