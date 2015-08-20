/**
 * 
 */
package org.sharks.service.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.cache.warmer.CacheWarmer;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class ManagementEntityServiceCacheWarmer implements CacheWarmer {
	
	@Inject
	private ManagementEntityService service;
	
	@Inject
	private ManagementEntityDao entityDao;

	@Override
	public void warmup() {
		log.info("warming cache");
		
		log.trace("entities...");
		for (MgmtEntity entity:entityDao.list(ManagementEntityDao.RFMO_TYPE)) service.get(entity.getAcronym());
		for (MgmtEntity entity:entityDao.list(ManagementEntityDao.INSTITUTION_TYPE)) service.get(entity.getAcronym());
		log.trace("done");

		log.trace("cache warmup complete");
	}

}
