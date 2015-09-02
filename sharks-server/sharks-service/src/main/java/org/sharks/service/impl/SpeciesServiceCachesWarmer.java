package org.sharks.service.impl;

import javax.inject.Inject;

import org.sharks.service.SpeciesService;
import org.sharks.service.cache.warmer.CacheWarmer;
import org.sharks.service.dto.SpeciesEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SpeciesServiceCachesWarmer implements CacheWarmer {
	
	@Inject
	private SpeciesService service;

	@Override
	public void warmup() {
		service.list(true);
		for (SpeciesEntry entry:service.list(false)) {
			service.getSpecies(entry.getAlphaCode());
		}
	}
	
}