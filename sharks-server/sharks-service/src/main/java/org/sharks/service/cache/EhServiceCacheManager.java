/**
 * 
 */
package org.sharks.service.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class EhServiceCacheManager implements ServiceCacheManager {
	
	@Inject
	private Event<CacheEvent> events;
	@Inject
	private CacheManager cacheManager;
	private Map<String, List<EhServiceCache<?,?>>> servicesCaches = new HashMap<String, List<EhServiceCache<?,?>>>();

	@Override
	public <K, V> ServiceCache<K, V> getOrCreateCache(String serviceName, String cacheName) {
		
		String ehCacheName = getEhCacheName(serviceName, cacheName);
		
		boolean newCache = !cacheManager.cacheExists(ehCacheName);
		
		if (newCache) cacheManager.addCache(ehCacheName);
		
		EhServiceCache<K,V> cache = new EhServiceCache<K, V>(cacheManager.getCache(ehCacheName));
		
		if (newCache) {
			addCache(serviceName, cache);
			events.fire(new CacheEvent.CacheAdded(serviceName, cacheName));
		}
		
		return cache;
	}
	
	private String getEhCacheName(String serviceName, String cacheName) {
		return serviceName + "." + cacheName;
	}
	
	private void addCache(String serviceName, EhServiceCache<?, ?> cache) {
		List<EhServiceCache<?, ?>> caches = servicesCaches.get(serviceName);
		
		if (caches == null) {
			caches = new ArrayList<EhServiceCache<?,?>>();
			servicesCaches.put(serviceName, caches);
		}
		
		caches.add(cache);
	}
	
	private List<EhServiceCache<?, ?>> getServiceCaches(String serviceName) {
		List<EhServiceCache<?, ?>> caches = servicesCaches.get(serviceName);
		return caches!=null?caches:Collections.emptyList();
	}
	
	@PreDestroy
	private void shutdownCacheManager() {
		log.info("shutting down the cache manager");
		cacheManager.shutdown();
	}

	@Override
	public void clearCaches(String... services) {
		for (String service:services) {
			List<EhServiceCache<?, ?>> caches = getServiceCaches(service);
			for (EhServiceCache<?,?> cache:caches) cache.clear();
		}
		
		events.fire(new CacheEvent.CachesCleaned(services));
	}

	@Override
	public void flushCaches(String... services) {
		for (String service:services) {
			List<EhServiceCache<?, ?>> caches = getServiceCaches(service);
			for (EhServiceCache<?,?> cache:caches) cache.flush();
		}
		
		events.fire(new CacheEvent.CachesFlushed(services));
	}

}
