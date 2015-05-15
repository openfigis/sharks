/**
 * 
 */
package org.sharks.service.cache;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.config.Configuration;
import org.sharks.service.event.ApplicationEvent;
import org.sharks.service.moniker.MonikerService;
import org.sharks.service.refpub.RefPubService;
import org.sharks.storage.dao.CountryDao;
import org.sharks.storage.dao.SpeciesDao;
import org.sharks.storage.domain.Country;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class CachesWarmer {

	@Inject
	private SpeciesDao speciesDao;
	
	@Inject
	private CountryDao countryDao;
	
	@Inject
	private RefPubService refPubService;
	
	@Inject
	private MonikerService monikerService;
	
	
	void cacheWarmup(@Observes ApplicationEvent.Startup startup, Configuration configuration) {
		if (configuration.isCacheWarmupEnabled()) {
			
			refPubCacheWarmup();
			monikersCacheWarmup();
			
		} else log.info("cache warmup disabled");
	}
	
	private void refPubCacheWarmup() {
		log.info("Warming RefPub cache");
		
		log.trace("species...");
		for (Species species:speciesDao.listWithMeasures()) refPubService.getSpecies(species.getAlphaCode());
		log.trace("done");
		
		log.trace("countries...");
		for (Country country:countryDao.listWithPoAs()) refPubService.getCountry(country.getCode());
		log.trace("done");

		log.trace("RefPub cache warmup complete");
	}
	
	private void monikersCacheWarmup() {
		log.info("Warming Monikers cache");
		
		log.trace("countries...");
		for (Country country:countryDao.listWithPoAs()) monikerService.getRfbsForCountry(country.getCode());
		log.trace("done");

		log.trace("Monikers cache warmup complete");
	}
	
}
