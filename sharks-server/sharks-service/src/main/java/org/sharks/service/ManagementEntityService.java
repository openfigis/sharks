/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.EntityEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ManagementEntityService {
	
	EntityDetails get(String acronym);
	
	List<EntityEntry> list();

}
