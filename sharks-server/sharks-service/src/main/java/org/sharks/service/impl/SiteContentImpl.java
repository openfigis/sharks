/**
 * 
 */
package org.sharks.service.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.Service;
import org.sharks.service.SiteContentService;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.Cached;
import org.sharks.service.dto.SiteContent;
import org.sharks.storage.dao.ConfigTextDao;
import org.sharks.storage.domain.ConfigText;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
@Service(name="content",type=ServiceType.INTERNAL)
public class SiteContentImpl implements SiteContentService {
	
	@Inject
	private ConfigTextDao dao;

	@Override @Cached("get")
	public SiteContent get(String keyword) {
		ConfigText text = dao.getByKeyword(keyword);
		return text!=null?new SiteContent(keyword, text.getDescription()):null;
	}

}
