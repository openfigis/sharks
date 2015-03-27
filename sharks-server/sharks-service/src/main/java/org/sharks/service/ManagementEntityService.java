/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ManagementEntityService {
	
	List<MgmtEntity> list();
}
