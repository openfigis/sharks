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
	private Map<ServiceInfo, List<EhServiceCache<?,?>>> servicesCaches = new HashMap<ServiceInfo, List<EhServiceCache<?,?>>>();

	@Override
	public <K, V> ServiceCache<K, V> getOrCreateCache(ServiceInfo service, String cacheName) {
		
		String ehCacheName = getEhCacheName(service.getName(), cacheName);
		
		boolean newCache = !cacheManager.cacheExists(ehCacheName);
		
		if (newCache) cacheManager.addCache(ehCacheName);
		
		EhServiceCache<K,V> cache = new EhServiceCache<K, V>(cacheManager.getCache(ehCacheName));
		
		if (newCache) {
			addCache(service, cache);
			events.fire(new CacheEvent.CacheAdded(service.getName(), cacheName));
		}
		
		return cache;
	}
	
	private String getEhCacheName(String serviceName, String cacheName) {
		return serviceName + "." + cacheName;
	}
	
	private void addCache(ServiceInfo service, EhServiceCache<?, ?> cache) {
		List<EhServiceCache<?, ?>> caches = servicesCaches.get(service);
		
		if (caches == null) {
			caches = new ArrayList<EhServiceCache<?,?>>();
			servicesCaches.put(service, caches);
		}
		
		caches.add(cache);
	}
	
	private List<EhServiceCache<?, ?>> getServiceCaches(String serviceName) {
		for (ServiceInfo service:servicesCaches.keySet()) {
			if (service.getName().equals(serviceName)) return servicesCaches.get(service);
		}
		return Collections.emptyList();
	}
	
	@PreDestroy
	private void shutdownCacheManager() {
		log.info("shutting down the cache manager");
		cacheManager.shutdown();
	}

	@Override
	public void clearCaches(String ... services) {
		for (String service:services) {
			List<EhServiceCache<?, ?>> caches = getServiceCaches(service);
			for (EhServiceCache<?,?> cache:caches) cache.clear();
		}
		
		events.fire(new CacheEvent.CachesCleaned(services));
	}

	@Override
	public void flushCaches(String ... services) {
		for (String service:services) {
			List<EhServiceCache<?, ?>> caches = getServiceCaches(service);
			for (EhServiceCache<?,?> cache:caches) cache.flush();
		}
		
		events.fire(new CacheEvent.CachesFlushed(services));
	}

}
