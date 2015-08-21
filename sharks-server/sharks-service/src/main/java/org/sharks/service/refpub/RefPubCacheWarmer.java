/**
 * 
 */
package org.sharks.service.refpub;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.cache.warmer.CacheWarmer;
import org.sharks.service.cache.warmer.CacheWarmer.HighPriority;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.dao.SpeciesDao;
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton @Slf4j @HighPriority
public class RefPubCacheWarmer implements CacheWarmer {
	
	@Inject
	private RefPubService refPubService;
	
	@Inject
	private SpeciesDao speciesDao;
	
	@Inject
	private ManagementEntityDao entityDao;

	@Override
	public void warmup() {
		log.info("warming cache");
		
		log.trace("species...");
		for (Species species:speciesDao.list()) refPubService.getSpecies(species.getAlphaCode());
		log.trace("done");
		
		log.trace("countries...");
		for (MgmtEntity country:entityDao.list(ManagementEntityDao.COUNTRY_TYPE)) refPubService.getCountryByIso3(country.getAcronym());
		log.trace("done");

		log.trace("cache warmup complete");
		
	}

}
