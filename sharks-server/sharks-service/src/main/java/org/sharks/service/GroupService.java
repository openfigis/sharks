/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.GroupDetails;
import org.sharks.service.dto.GroupEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface GroupService {

	GroupDetails get(long code);
	List<GroupEntry> list();
}
