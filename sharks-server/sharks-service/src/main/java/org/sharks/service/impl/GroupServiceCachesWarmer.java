package org.sharks.service.impl;

import javax.inject.Inject;

import org.sharks.service.GroupService;
import org.sharks.service.cache.warmer.CacheWarmer;
import org.sharks.service.dto.GroupEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public final class GroupServiceCachesWarmer implements CacheWarmer {

	@Inject
	GroupService service;
	
	@Override
	public void warmup() {
		for (GroupEntry entry:service.list()) {
			service.get(entry.getCode());
		}
	}
	
}