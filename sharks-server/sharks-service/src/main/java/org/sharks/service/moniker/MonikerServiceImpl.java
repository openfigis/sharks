/**
 * 
 */
package org.sharks.service.moniker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.cache.ServiceCache;
import org.sharks.service.cache.CacheName;
import org.sharks.service.cache.ServiceCache.CacheElement;
import org.sharks.service.moniker.dto.FaoLexDocument;
import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.service.moniker.dto.RfbEntry;
import org.sharks.service.moniker.rest.MonikersRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class MonikerServiceImpl implements MonikerService {
	
	@Inject
	private MonikersRestClient restClient;
	
	@Inject @CacheName("rfb4iso3")
	private ServiceCache<String, List<RfbEntry>> rfb4Iso3Cache;
	
	@Inject @CacheName("rfb")
	private ServiceCache<String, RfbEntry> rfbCache;
	
	@Inject @CacheName("figisdoc")
	private ServiceCache<String, FigisDoc> figisDocCache;
	
	@Inject @CacheName("faoLexDocument")
	private ServiceCache<String, List<FaoLexDocument>> faoLexDocumentCache;

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
		FigisDoc doc = getFigisDocById(figisId);
		return doc!=null?doc.getAcronym():null;
	}
	
	private FigisDoc getFigisDocById(String figisId) {
		
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

	@Override
	public FigisDoc getFigisDocByAcronym(String rfbAcronym) {
		
		RfbEntry entry = getRfb(rfbAcronym);
		if (entry == null || entry.getFid() == null) return null;
		
		return getFigisDocById(entry.getFid());
	}
	
	private RfbEntry getRfb(String acronym) {
		CacheElement<RfbEntry> cacheElement =  rfbCache.get(acronym);
		if (cacheElement.isPresent()) return cacheElement.getValue();

		try {
			RfbEntry entry = restClient.getRfb(acronym);
			
			if (entry == null) log.warn("RfbEntry for acronym "+acronym+" not found");
			
			rfbCache.put(acronym, entry);
			return entry;
		} catch(Exception e) {
			log.error("Failed retrieving RfbEntry for acronym "+acronym, e);
			return null;
		}
	}

	@Override
	public List<FaoLexDocument> getFaoLexDocumentsForCountry(String countryIso3) {
		return getFaoLexDocuments(countryIso3);
	}
	
	private List<FaoLexDocument> getFaoLexDocuments(String countryIso3) {
		
		CacheElement<List<FaoLexDocument>> cacheElement = faoLexDocumentCache.get(countryIso3);
		if (cacheElement.isPresent()) return cacheElement.getValue();
		
		try {
			List<FaoLexDocument> docs = restClient.getFaoLexDocuments(countryIso3);
			
			if (docs == null) {
				log.warn("FaoLexDocuments for country "+countryIso3+" not found");
				docs = Collections.emptyList();
			}
			
			faoLexDocumentCache.put(countryIso3, docs);
			
			return docs;
		} catch(Exception e) {
			log.error("Failed retrieving FaoLexDocuments list for country "+countryIso3, e);
			return Collections.emptyList();
		}
	}

}
