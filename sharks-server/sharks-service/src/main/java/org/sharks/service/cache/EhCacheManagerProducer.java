/**
 * 
 */
package org.sharks.service.cache;

import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.config.Configuration;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.ConfigurationFactory;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@ApplicationScoped
public class EhCacheManagerProducer {
	
	@Produces @Singleton
	public CacheManager produce(Configuration configuration) {
		InputStream cacheConfigurationStream = EhServiceCacheManagerTest.class.getResourceAsStream("cache.xml");
		
		net.sf.ehcache.config.Configuration cacheConfiguration = ConfigurationFactory.parseConfiguration(cacheConfigurationStream);
		
		String cacheLocation = configuration.getCacheLocation();
		if (cacheLocation!=null) {
			log.info("Setting cache location to {}", cacheLocation);
			cacheConfiguration.getDiskStoreConfiguration().setPath(cacheLocation);
		}
		
		return CacheManager.newInstance(cacheConfiguration);
	}

}
