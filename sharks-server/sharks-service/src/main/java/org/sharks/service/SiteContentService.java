/**
 * 
 */
package org.sharks.service;

import org.sharks.storage.domain.SiteContent;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface SiteContentService {
	
	public SiteContent get(String keyword);

}
