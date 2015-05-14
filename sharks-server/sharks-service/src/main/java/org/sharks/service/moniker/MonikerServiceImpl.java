/**
 * 
 */
package org.sharks.service.moniker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.cache.Cache;
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
	
	@Inject
	private Cache<String, List<String>> acronymsCache;

	@Override
	public List<String> getRfbsForCountry(String countryIso3) {
		if (acronymsCache.contains(countryIso3)) return acronymsCache.get(countryIso3);
		
		List<RfbEntry> rfbs = getRfbEntries(countryIso3);
		List<String> acronyms = toAcronyms(rfbs);
		
		acronymsCache.put(countryIso3, acronyms);
		return acronyms;
	}
	
	private List<RfbEntry> getRfbEntries(String countryIso3) {
		try {
			List<RfbEntry> rfbs = restClient.getRfbs(countryIso3);
			if (rfbs == null) {
				log.warn("Rfbs for country "+countryIso3+" not found");
				return Collections.emptyList();
			}
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
		try {
			FigisDoc doc = restClient.getFigisDoc(figisId);
			if (doc == null) {
				log.warn("FigisDoc for figisId "+figisId+" not found");
				return null;
			}
			return doc.getAcronym();
		} catch(Exception e) {
			log.error("Failed retrieving FigisDoc for figisId "+figisId, e);
			return null;
		}
	}

}
