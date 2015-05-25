/**
 * 
 */
package org.sharks.service.moniker;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.cache.warmer.CacheWarmer;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class MonikersCacheWarmer implements CacheWarmer {
	
	@Inject
	private MonikerService monikerService;
	
	@Inject
	private ManagementEntityDao entityDao;

	@Override
	public void warmup() {
		log.info("warming cache");
		
		log.trace("monikers countries...");
		for (MgmtEntity country:entityDao.list(ManagementEntityDao.COUNTRY_TYPE)) monikerService.getRfbsForCountry(country.getAcronym());
		log.trace("monikers done");
		
		log.trace("entities...");
		for (MgmtEntity entity:entityDao.list(ManagementEntityDao.RFMO_TYPE)) monikerService.getFigisDocByAcronym(entity.getAcronym());
		for (MgmtEntity entity:entityDao.list(ManagementEntityDao.INSTITUTION_TYPE)) monikerService.getFigisDocByAcronym(entity.getAcronym());
		log.trace("done");

		log.trace("cache warmup complete");
	}

}
