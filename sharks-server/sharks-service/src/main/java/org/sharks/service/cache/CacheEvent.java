package org.sharks.service.cache;

import java.util.List;

import org.sharks.service.cache.ServiceCacheManager.ServiceInfo;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CacheEvent {
	
	@Data
	public class CacheAdded implements CacheEvent {
		private final ServiceInfo service;
		private final String cacheName;
	}

	@Data
	public class CachesCleaned implements CacheEvent {
		private final List<ServiceInfo> services;
	}
	
	@Data
	public class CachesFlushed implements CacheEvent {
		private final List<ServiceInfo> services;
	}

}
