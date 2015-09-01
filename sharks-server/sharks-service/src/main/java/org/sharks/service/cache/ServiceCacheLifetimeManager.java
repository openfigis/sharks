/**
 * 
 */
package org.sharks.service.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.config.Configuration.Time;

import lombok.extern.slf4j.Slf4j;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@Singleton
public class ServiceCacheLifetimeManager {
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private Map<Time, List<String>> scheduledExpiration = new HashMap<Time, List<String>>(); 
	
	@Inject
	private ServiceCacheManager cacheManager;
	
	public void scheduleExpiration(String serviceName, Time time) {
		
		List<String> services = scheduledExpiration.get(time);
		
		if (services == null) {
			services = new ArrayList<String>();
			scheduledExpiration.put(time, services);
			services.add(serviceName);
			scheduleTimer(time);
		} else services.add(serviceName);
		
		log.info("Scheduled expiration for {} to {}", serviceName, time);
	}
	
	private void scheduleTimer(final Time time) {
		scheduler.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				expire(time);
			}
		}, time.getValue(), time.getValue(), time.getUnit());
	}
	
	private void expire(Time time) {
		List<String> services = scheduledExpiration.get(time);
		log.info("Caches expired for services {}", services);
		cacheManager.clearCaches(services.toArray(new String[services.size()]));
	}
	
	@PreDestroy
	private void destroy() {
		scheduler.shutdown();
	}

}
