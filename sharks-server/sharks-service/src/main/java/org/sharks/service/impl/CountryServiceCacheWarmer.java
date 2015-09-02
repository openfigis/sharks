package org.sharks.service.impl;

import javax.inject.Inject;

import org.sharks.service.CountryService;
import org.sharks.service.cache.warmer.CacheWarmer;
import org.sharks.service.dto.CountryEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CountryServiceCacheWarmer implements CacheWarmer {
	
	@Inject
	CountryService service;

	@Override
	public void warmup() {
		service.list(true);
		for (CountryEntry entry:service.list(false)) {
			service.get(entry.getCode());
		}
	}
	
}