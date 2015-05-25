/**
 * 
 */
package org.sharks.service.cache.warmer;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.geoserver.GeoServerService;
import org.sharks.service.moniker.MonikerService;
import org.sharks.service.refpub.RefPubService;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.dao.SpeciesDao;
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class CachesWarmer {

	@Inject
	private SpeciesDao speciesDao;
	
	@Inject
	private ManagementEntityDao entityDao;
	
	@Inject
	private RefPubService refPubService;
	
	@Inject
	private MonikerService monikerService;
	
	@Inject
	private GeoServerService geoServerService;
	
	@Inject
	private CacheWarmingExecutor executor;
	
	public void warmupCaches() {
		
		executor.warm(()->refPubCacheWarmup());
		executor.warm(()->monikersCacheWarmup());
		executor.warm(()->geoserverCacheWarmup());

	}
	
	private void refPubCacheWarmup() {
		log.info("Warming RefPub cache");
		
		log.trace("species...");
		for (Species species:speciesDao.list()) refPubService.getSpecies(species.getAlphaCode());
		log.trace("done");
		
		log.trace("countries...");
		for (MgmtEntity country:entityDao.list(ManagementEntityDao.COUNTRY_TYPE)) refPubService.getCountry(country.getAcronym());
		log.trace("done");

		log.trace("RefPub cache warmup complete");
	}
	
	private void monikersCacheWarmup() {
		log.info("Warming Monikers cache");
		
		log.trace("countries...");
		for (MgmtEntity country:entityDao.list(ManagementEntityDao.COUNTRY_TYPE)) monikerService.getRfbsForCountry(country.getAcronym());
		log.trace("done");
		
		log.trace("entities...");
		for (MgmtEntity entity:entityDao.list(ManagementEntityDao.RFMO_TYPE)) monikerService.getFigisDocByAcronym(entity.getAcronym());
		for (MgmtEntity entity:entityDao.list(ManagementEntityDao.INSTITUTION_TYPE)) monikerService.getFigisDocByAcronym(entity.getAcronym());
		log.trace("done");

		log.trace("Monikers cache warmup complete");
	}
	
	private void geoserverCacheWarmup() {
		log.info("Warming GeoServer cache");
		
		geoServerService.hasSpeciesDistributionMap("ANY");
		
		log.trace("GeoServer cache warmup complete");
	}
	
}
