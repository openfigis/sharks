/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_ENTITY_DOCUMENT;
import static org.sharks.service.producer.EntryProducers.TO_FAOLEX_DOCUMENT;
import static org.sharks.service.producer.EntryProducers.TO_POA_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.CountryService;
import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.Cached;
import org.sharks.service.dto.CountryDetails;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.moniker.MonikerService;
import org.sharks.service.moniker.dto.FaoLexFiDocument;
import org.sharks.service.producer.CountryEntityProducer;
import org.sharks.service.producer.CountryEntryProducer;
import org.sharks.storage.dao.InformationSourceDao;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
@Service(name="country",type=ServiceType.INTERNAL)
public class CountryServiceImpl implements CountryService {
	
	@Inject
	private ManagementEntityDao dao;
	
	@Inject
	private CountryEntryProducer entryProducer;
	
	@Inject
	private MonikerService monikers;
	
	@Inject
	private CountryEntityProducer entityEntryProducer;
	
	@Override @Cached("get")
	public CountryDetails get(String code) {

		MgmtEntity country = dao.getByAcronym(code);
		if (country == null) return null;
		
		List<String> rfbs = monikers.getRfbsForCountry(code);
		List<FaoLexFiDocument> docs = monikers.getFaoLexDocumentsForCountry(code);
		
		CountryDetails details = new CountryDetails(country.getAcronym(), 
				country.getMgmtEntityName(), 
				convert(rfbs, entityEntryProducer),
				convert(country.getPoAs(), TO_POA_ENTRY),
				convert(onlyOthers(country.getInformationSources()), TO_ENTITY_DOCUMENT),
				convert(docs, TO_FAOLEX_DOCUMENT)
				);
		return details;
	}
	
	private List<InformationSource> onlyOthers(List<InformationSource> sources) {
		return sources.stream()
				.filter(source->source.getInformationType().getCode().equals(InformationSourceDao.OTHER_TYPE))
				.collect(Collectors.toList());		
	}

	@Override @Cached("list")
	public List<CountryEntry> list(boolean onlyWithPoAs) {
		List<MgmtEntity> countries = dao.listCountries(onlyWithPoAs, false);
		List<CountryEntry> entries = convert(countries, entryProducer);
		return entries;
	}

}
