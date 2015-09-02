/**
 * 
 */
package org.sharks.service.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.Service.ServiceType;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Cache;

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
	private Map<String, EhServiceCache<?,?>> caches = new HashMap<String, EhServiceCache<?,?>>();
	private Map<ServiceInfo, List<EhServiceCache<?,?>>> servicesCaches = new HashMap<ServiceInfo, List<EhServiceCache<?,?>>>();

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> ServiceCache<K, V> getOrCreateCache(ServiceInfo service, String cacheName) {
		String ehCacheName = getEhCacheName(service, cacheName);
		
		EhServiceCache<?,?> cache = caches.get(ehCacheName);
		if (cache!=null) return (ServiceCache<K, V>) cache;
		
		synchronized (cacheManager) {
			if (!cacheManager.cacheExists(ehCacheName)) cacheManager.addCache(ehCacheName);
		}
		
		Cache ehCache = cacheManager.getCache(ehCacheName);
		cache = new EhServiceCache<K, V>(ehCache);
		
		caches.put(ehCacheName, cache);
		addServiceCache(service, cache);
		
		events.fire(new CacheEvent.CacheAdded(service, cacheName));
		
		return (ServiceCache<K, V>) cache;
	}
	
	private String getEhCacheName(ServiceInfo service, String cacheName) {
		return service.getName() + "." + cacheName;
	}
	
	private void addServiceCache(ServiceInfo service, EhServiceCache<?, ?> cache) {
		List<EhServiceCache<?, ?>> caches = servicesCaches.get(service);
		
		if (caches == null) {
			caches = new ArrayList<EhServiceCache<?,?>>();
			servicesCaches.put(service, caches);
		}
		
		caches.add(cache);
	}
	
	@PreDestroy
	private void shutdownCacheManager() {
		log.info("shutting down the cache manager");
		cacheManager.shutdown();
	}

	@Override
	public void clearCaches(String ... servicesNames) {
		List<ServiceInfo> services = getServicesByName(Arrays.asList(servicesNames));
		clear(services);		
	}
	
	@Override
	public void flushCaches(String ... servicesNames) {
		List<ServiceInfo> services = getServicesByName(Arrays.asList(servicesNames));
		flush(services);
	}

	@Override
	public void clearCaches(ServiceType ... types) {
		List<ServiceInfo> services = getServicesByType(Arrays.asList(types));
		clear(services);
	}

	@Override
	public void flushCaches(ServiceType ... types) {
		List<ServiceInfo> services = getServicesByType(Arrays.asList(types));
		flush(services);
	}
	
	private void clear(List<ServiceInfo> services) {
		log.trace("clear {}", services);
		for (ServiceInfo service:services) {
			List<EhServiceCache<?, ?>> caches = getServiceCaches(service);
			for (EhServiceCache<?,?> cache:caches) cache.clear();
		}
		
		events.fire(new CacheEvent.CachesCleaned(services));
	}
	
	private void flush(List<ServiceInfo> services) {
		log.trace("flush {}", services);
		for (ServiceInfo service:services) {
			List<EhServiceCache<?, ?>> caches = getServiceCaches(service);
			for (EhServiceCache<?,?> cache:caches) cache.flush();
		}
		
		events.fire(new CacheEvent.CachesFlushed(services));
	}
	
	private List<ServiceInfo> getServicesByType(List<ServiceType> types) {
		List<ServiceInfo> services = new ArrayList<ServiceInfo>();
		for (ServiceInfo service:servicesCaches.keySet()) {
			if (types.contains(service.getType())) services.add(service);
		}
		
		return services;
	}
	
	private List<ServiceInfo> getServicesByName(List<String> names) {
		List<ServiceInfo> services = new ArrayList<ServiceInfo>();
		log.trace("current services "+servicesCaches.keySet());
		for (ServiceInfo service:servicesCaches.keySet()) {
			log.trace("service "+service.getName());
			if (names.contains(service.getName())) services.add(service);
		}
		
		return services;
	}
	
	private List<EhServiceCache<?, ?>> getServiceCaches(ServiceInfo service) {
		List<EhServiceCache<?, ?>> caches = servicesCaches.get(service);
		return caches!=null?caches:Collections.emptyList();
	}
}
