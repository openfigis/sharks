/**
 * 
 */
package org.sharks.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.config.Configuration;
import org.sharks.service.cache.warmer.CacheWarmingExecutor;
import org.sharks.service.cache.warmer.NopCacheWarmingExecutor;
import org.sharks.service.cache.warmer.ParallelCacheWarmingExecutor;
import org.sharks.service.cache.warmer.SequentialCacheWarmerExecutor;
import org.sharks.service.geoserver.rest.GeoServerRestClient;
import org.sharks.service.http.HttpClient;
import org.sharks.service.indexing.IndexingService;
import org.sharks.service.indexing.SolrDocumentProviders;
import org.sharks.service.indexing.SolrIndexingService;
import org.sharks.service.indexing.SolrIndexingService.SolrDocumentProvider;
import org.sharks.service.kor.rest.KorParser;
import org.sharks.service.kor.rest.KorRestClient;
import org.sharks.service.moniker.rest.MonikersRestClient;
import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Slf4j
public class Producers {

	@Produces @Singleton
	public RefPubRestClient getRefPubRestClient(Configuration configuration, HttpClient httpClient) {
		return new RefPubRestClient(configuration.getRefPubUrl(), httpClient);
	}

	@Produces @Singleton
	public MonikersRestClient getMonikerRestClient(Configuration configuration, HttpClient httpClient) {
		return new MonikersRestClient(configuration.getMonikersUrl(), httpClient);
	}
	
	@Produces @Singleton
	public GeoServerRestClient getGeoServerRestClient(Configuration configuration, HttpClient httpClient) {
		return new GeoServerRestClient(httpClient, configuration.getGeoServerSpeciesListUrl());
	}
	
	@Produces @Singleton
	public KorRestClient getKorRestClient(Configuration configuration, HttpClient httpClient, KorParser parser) {
		return new KorRestClient(httpClient, configuration.getKorResourcesUrl(), parser);
	}
	
	@Produces @Singleton
	public CacheWarmingExecutor getCacheWarmingExecutor(Configuration configuration) {
		switch (configuration.getCacheWarmupType()) {
			case NONE: return new NopCacheWarmingExecutor();
			case SEQUENTIAL: return new SequentialCacheWarmerExecutor();
			case PARALLEL: return new ParallelCacheWarmingExecutor();
			default: {
				log.error("Unkknow cache warmup option "+configuration.getCacheWarmupType());
				return new NopCacheWarmingExecutor();
			}
		}
	}

	@Produces
	public IndexingService getIndexingService(Configuration configuration) {
		if (configuration.getSolrUrl() == null || configuration.getSolrUrl().isEmpty()) {
			log.warn("Solr Url missing from configuration");
			return null;
		}

		List<SolrDocumentProvider<?>> providers = Arrays.asList(SolrDocumentProviders.MEASURE, SolrDocumentProviders.POA);
		log.trace("providers:");
		for (SolrDocumentProvider<?> provider:providers) log.trace("provider for {}",provider.getType().getSimpleName());

		try {
			return new SolrIndexingService(configuration.getSolrUrl(), providers);
		} catch(Exception e) {
			log.error("Solar indexing service failed", e);
			return null;
		}
	}

}
