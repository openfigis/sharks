/**
 * 
 */
package org.sharks.service.cites;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.config.Configuration;
import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.CacheName;
import org.sharks.service.cache.ServiceCache;
import org.sharks.service.cache.ServiceCache.CacheElement;
import org.sharks.service.http.HttpClient;
import org.sharks.service.informea.dto.InformeaCountry;
import org.sharks.service.informea.dto.InformeaParties;
import org.sharks.service.informea.rest.InformeaRestClient;

import lombok.extern.slf4j.Slf4j;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@Singleton
@Service(name = "cites", type = ServiceType.EXTERNAL)
public class CitesServiceImpl implements CitesService {

	private final static String CACHE_KEY = "parties";

	private InformeaRestClient restClient;

	@Inject
	@CacheName("parties")
	private ServiceCache<String, InformeaParties> cache;

	@Inject
	public CitesServiceImpl(HttpClient httpClient, Configuration configuration) {
		restClient = new InformeaRestClient(httpClient, configuration.getCitesPartiesUrl());
	}

	@Override
	public List<InformeaCountry> getParties() {

		CacheElement<InformeaParties> element = cache.get(CACHE_KEY);
		if (element.isPresent())
			return element.getValue().getCountries();

		InformeaParties parties = getCitesParties();

		if (parties == null)
			return Collections.emptyList();

		cache.put(CACHE_KEY, parties);

		return parties.getCountries();
	}

	private InformeaParties getCitesParties() {
		try {
			return restClient.getParties();
		} catch (Exception e) {
			log.error("Failed retrieving cites parties", e);
			return null;
		}
	}

	@Override
	public Optional<InformeaCountry> getMember(String iso3) {
		List<InformeaCountry> parties = getParties();
		//Kiran : Added a hardcoded fix for EU to work as there is not iso3 code named EUR in the cites
		return parties.stream().filter(t -> (t.getIso3() != null && t.getIso3().equals(iso3)) || (iso3!=null && iso3.equalsIgnoreCase("eur") && t.getIso2().equalsIgnoreCase("eu"))).findFirst();
	}

}
