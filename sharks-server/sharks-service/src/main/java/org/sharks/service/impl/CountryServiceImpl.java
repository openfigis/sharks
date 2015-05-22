/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_ENTITY_DOCUMENT;
import static org.sharks.service.producer.EntryProducers.TO_POA_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.sharks.service.CountryService;
import org.sharks.service.dto.CountryDetails;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.moniker.MonikerService;
import org.sharks.service.producer.CountryEntryProducer;
import org.sharks.service.producer.EntityEntryProducer;
import org.sharks.storage.dao.InformationSourceDao;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CountryServiceImpl implements CountryService {
	
	@Inject
	private ManagementEntityDao dao;
	
	@Inject
	private CountryEntryProducer entryProducer;
	
	@Inject
	private MonikerService monikers;
	
	@Inject
	private EntityEntryProducer entityEntryProducer;
	
	@Override
	public CountryDetails get(String code) {
		
		MgmtEntity country = dao.getByAcronym(code);
		if (country == null) return null;
		
		List<String> rfbs = monikers.getRfbsForCountry(code);
		CountryDetails details = new CountryDetails(country.getAcronym(), 
				country.getMgmtEntityName(), 
				convert(rfbs, entityEntryProducer),
				convert(country.getPoAs(), TO_POA_ENTRY),
				convert(onlyOthers(country.getInformationSources()), TO_ENTITY_DOCUMENT));
		return details;
	}
	
	private List<InformationSource> onlyOthers(List<InformationSource> sources) {
		return sources.stream()
				.filter(source->source.getInformationType().getCode().equals(InformationSourceDao.OTHER_TYPE))
				.collect(Collectors.toList());
				
				
	}

	@Override
	public List<CountryEntry> list(boolean onlyWithPoAs) {
		List<MgmtEntity> countries = dao.listCountries(onlyWithPoAs, false);
		return convert(countries, entryProducer);
	}

}
