package org.sharks.service;

import org.sharks.service.dto.ClearCacheStatus;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CacheService {

	public void clearExternalCaches();
	public void clearCaches(String ... services);
	public void flushCaches(String ... services);

	ClearCacheStatus getClearCacheStatus();
	public void asyncClearExternalCaches();


	public class CacheLoadedEvent {
	}

}