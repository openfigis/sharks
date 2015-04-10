/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.EntityEntry;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ManagementEntityService {
	
	List<EntityEntry> list();
	
	List<Country> getCountries(String acronym);
	
	List<EntityEntry> getEntitiesForCountry(String countryCode);
}
