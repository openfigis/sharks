/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.EntityDetails;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ManagementEntityService {
	
	List<EntityDetails> list();
}
