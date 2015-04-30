/**
 * 
 */
package org.sharks.service.impl;

import javax.inject.Inject;

import org.sharks.service.SiteContentService;
import org.sharks.storage.dao.SiteContentDao;
import org.sharks.storage.domain.SiteContent;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SiteContentImpl implements SiteContentService {
	
	@Inject
	private SiteContentDao dao;

	@Override
	public SiteContent get(String keyword) {
		return dao.get(keyword);
	}

}
