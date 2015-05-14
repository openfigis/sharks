/**
 * 
 */
package org.sharks.service.impl;

import javax.inject.Inject;

import org.sharks.service.SiteContentService;
import org.sharks.service.dto.SiteContent;
import org.sharks.storage.dao.ConfigTextDao;
import org.sharks.storage.domain.ConfigText;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SiteContentImpl implements SiteContentService {
	
	@Inject
	private ConfigTextDao dao;

	@Override
	public SiteContent get(String keyword) {
		
		//TODO cache
		
		ConfigText text = dao.getByKeyword(keyword);
		return text!=null?new SiteContent(keyword, text.getDescription()):null;
	}

}
