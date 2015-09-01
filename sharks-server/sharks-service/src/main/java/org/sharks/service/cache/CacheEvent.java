package org.sharks.service.cache;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CacheEvent {
	
	@Data
	public class CacheAdded implements CacheEvent {
		private final String serviceName;
		private final String cacheName;
	}

	@Data
	public class CachesCleaned implements CacheEvent {
		private final String[] services;
	}
	
	@Data
	public class CachesFlushed implements CacheEvent {
		private final String[] services;
	}

}
