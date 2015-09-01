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

import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.ServiceCache;
import org.sharks.service.cache.CacheName;
import org.sharks.service.cache.ServiceCache.CacheElement;
import org.sharks.service.moniker.dto.FaoLexFiDocument;
import org.sharks.service.moniker.dto.Rfb;
import org.sharks.service.moniker.rest.MonikersRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j 
@Singleton
@Service(name="monikers",type=ServiceType.EXTERNAL)
public class MonikerServiceImpl implements MonikerService {
	
	@Inject
	private MonikersRestClient restClient;
	
	@Inject @CacheName("rfb4iso3")
	private ServiceCache<String, List<Rfb>> rfb4Iso3Cache;
	
	@Inject @CacheName("rfb")
	private ServiceCache<String, Rfb> rfbCache;
	
	@Inject @CacheName("rfb4fid")
	private ServiceCache<String, Rfb> rfb4FidCache;
	
	@Inject @CacheName("faoLexDocument")
	private ServiceCache<String, List<FaoLexFiDocument>> faoLexDocumentCache;

	@Override
	public List<String> getRfbsForCountry(String countryIso3) {
		
		List<Rfb> rfbs = getRfbEntries(countryIso3);
		List<String> acronyms = toAcronyms(rfbs);
		
		return acronyms;
	}
	
	private List<Rfb> getRfbEntries(String countryIso3) {
		
		CacheElement<List<Rfb>> cacheElement =  rfb4Iso3Cache.get(countryIso3);
		if (cacheElement.isPresent()) return cacheElement.getValue();
		
		try {
			List<Rfb> rfbs = restClient.getRfb4Iso3(countryIso3);
			
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
	
	private List<String> toAcronyms(List<Rfb> rfbs) {
		List<String> acronyms = new ArrayList<String>(rfbs.size());
		for (Rfb rfb:rfbs) {
			String acronym = toAcronym(rfb.getFigisId());
			if (acronym!=null) acronyms.add(acronym);
		}
		
		return acronyms;
	}
	
	private String toAcronym(String figisId) {
		Rfb rfb = getRfbByFid(figisId);
		return (rfb!=null)?rfb.getAcronym():null;
	}
	
	private Rfb getRfbByFid(String fid) {
		CacheElement<Rfb> cacheElement =  rfb4FidCache.get(fid);
		if (cacheElement.isPresent()) return cacheElement.getValue();
		
		try {
			Rfb rfb = restClient.getRfbByFid(fid);
			
			if (rfb == null) log.warn("Rfb for fid "+fid+" not found");
			
			rfb4FidCache.put(fid, rfb);
			return rfb;
		} catch(Exception e) {
			log.error("Failed retrieving Rfb for fid "+fid, e);
			return null;
		}
		
	}
	
	@Override
	public Rfb getRfb(String acronym) {
		CacheElement<Rfb> cacheElement =  rfbCache.get(acronym);
		if (cacheElement.isPresent()) return cacheElement.getValue();

		try {
			Rfb rfb = restClient.getRfbByAcronym(acronym);
			
			if (rfb == null) log.warn("Rfb for acronym "+acronym+" not found");
			
			rfbCache.put(acronym, rfb);
			return rfb;
		} catch(Exception e) {
			log.error("Failed retrieving Rfb for acronym "+acronym, e);
			return null;
		}
	}

	@Override
	public List<FaoLexFiDocument> getFaoLexDocumentsForCountry(String countryIso3) {
		return getFaoLexDocuments(countryIso3);
	}
	
	private List<FaoLexFiDocument> getFaoLexDocuments(String countryIso3) {
		
		CacheElement<List<FaoLexFiDocument>> cacheElement = faoLexDocumentCache.get(countryIso3);
		if (cacheElement.isPresent()) return cacheElement.getValue();
		
		try {
			List<FaoLexFiDocument> docs = restClient.getFaoLexDocuments(countryIso3);
			
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
