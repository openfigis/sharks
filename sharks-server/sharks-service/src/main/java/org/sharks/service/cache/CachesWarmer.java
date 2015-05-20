/**
 * 
 */
package org.sharks.service.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.config.Configuration;
import org.sharks.service.moniker.MonikerService;
import org.sharks.service.refpub.RefPubService;
import org.sharks.storage.dao.CountryDao;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.dao.SpeciesDao;
import org.sharks.storage.domain.Country;
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class CachesWarmer {
	
	private ExecutorService executor = Executors.newFixedThreadPool(2);

	@Inject
	private SpeciesDao speciesDao;
	
	@Inject
	private CountryDao countryDao;
	
	@Inject
	private ManagementEntityDao entityDao;
	
	@Inject
	private RefPubService refPubService;
	
	@Inject
	private MonikerService monikerService;
	
	@Inject
	private Configuration configuration;
	
	public void warmupCaches() {
		if (configuration.isCacheWarmupEnabled()) runWarmers();
		else log.info("cache warmup disabled");
	}
	
	private void runWarmers() {
		Future<Void> refpubWarmer = executor.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				refPubCacheWarmup();
				return null;
			}
		});
		
		Future<Void> monikerWarmer = executor.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				monikersCacheWarmup();
				return null;
			}
		});
		
		try {
			refpubWarmer.get();
		} catch (Exception e) {
			log.error("refpub warmer failed", e);
		}
		
		try {
			monikerWarmer.get();
		} catch (Exception e) {
			log.error("moniker warmer failed", e);
		}
	}
	
	private void refPubCacheWarmup() {
		log.info("Warming RefPub cache");
		
		log.trace("species...");
		for (Species species:speciesDao.list()) refPubService.getSpecies(species.getAlphaCode());
		log.trace("done");
		
		log.trace("countries...");
		for (Country country:countryDao.list()) refPubService.getCountry(country.getCode());
		log.trace("done");

		log.trace("RefPub cache warmup complete");
	}
	
	private void monikersCacheWarmup() {
		log.info("Warming Monikers cache");
		
		log.trace("countries...");
		for (Country country:countryDao.list()) monikerService.getRfbsForCountry(country.getCode());
		log.trace("done");
		
		log.trace("entities...");
		for (MgmtEntity entity:entityDao.list()) monikerService.getFigisDocByAcronym(entity.getAcronym());
		log.trace("done");

		log.trace("Monikers cache warmup complete");
	}
	
}
