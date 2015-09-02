package org.sharks.service.impl;

import javax.inject.Inject;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.cache.warmer.CacheWarmer;
import org.sharks.service.dto.EntityEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ManagementEntityServiceCachesWarmer implements CacheWarmer {
	
	@Inject
	private ManagementEntityService service;

	@Override
	public void warmup() {
		service.list(true);
		for (EntityEntry entry:service.list(false)) {
			service.get(entry.getAcronym());
		}
	}
	
}