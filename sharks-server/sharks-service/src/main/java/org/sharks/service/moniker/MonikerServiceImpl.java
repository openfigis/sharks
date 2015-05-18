/**
 * 
 */
package org.sharks.service.moniker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.cache.ServiceCache;
import org.sharks.service.cache.CacheName;
import org.sharks.service.cache.ServiceCache.CacheElement;
import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.service.moniker.dto.RfbEntry;
import org.sharks.service.moniker.rest.MonikersRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class MonikerServiceImpl implements MonikerService {
	
	@Inject
	private MonikersRestClient restClient;
	
	@Inject @CacheName("rfb4iso3")
	private ServiceCache<String, List<RfbEntry>> rfb4Iso3Cache;
	
	@Inject @CacheName("figisdoc")
	private ServiceCache<String, FigisDoc> figisDocCache;

	@Override
	public List<String> getRfbsForCountry(String countryIso3) {
		
		
		List<RfbEntry> rfbs = getRfbEntries(countryIso3);
		List<String> acronyms = toAcronyms(rfbs);
		
		
		return acronyms;
	}
	
	private List<RfbEntry> getRfbEntries(String countryIso3) {
		
		CacheElement<List<RfbEntry>> cacheElement =  rfb4Iso3Cache.get(countryIso3);
		if (cacheElement.isPresent()) return cacheElement.getValue();
		
		try {
			List<RfbEntry> rfbs = restClient.getRfb4Iso3(countryIso3);
			
			if (rfbs == null) {
				log.warn("Rfbs for country "+countryIso3+" not found");
				rfbs = Collections.emptyList();
			}
			
			rfb4Iso3Cache.put(countryIso3, rfbs);
			
			return rfbs;
		} catch(Exception e) {
			log.error("Failed retrieving RFBs list for country "+countryIso3, e);
			return Collections.emptyList();
		}
	}
	
	private List<String> toAcronyms(List<RfbEntry> rfbs) {
		List<String> acronyms = new ArrayList<String>(rfbs.size());
		for (RfbEntry rfb:rfbs) {
			String acronym = toAcronym(rfb.getFigisId());
			if (acronym!=null) acronyms.add(acronym);
		}
		
		return acronyms;
	}
	
	private String toAcronym(String figisId) {
		FigisDoc doc = getFigisDoc(figisId);
		return doc!=null?doc.getAcronym():null;
	}
	
	private FigisDoc getFigisDoc(String figisId) {
		
		CacheElement<FigisDoc> cacheElement =  figisDocCache.get(figisId);
		if (cacheElement.isPresent()) return cacheElement.getValue();

		try {
			FigisDoc doc = restClient.getFigisDoc(figisId);
			
			if (doc == null) log.warn("FigisDoc for figisId "+figisId+" not found");
			
			figisDocCache.put(figisId, doc);
			return doc;
		} catch(Exception e) {
			log.error("Failed retrieving FigisDoc for figisId "+figisId, e);
			return null;
		}
	}

}