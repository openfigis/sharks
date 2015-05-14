/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_POA_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.CountryService;
import org.sharks.service.dto.CountryDetails;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.moniker.MonikerService;
import org.sharks.service.producer.CountryEntryProducer;
import org.sharks.service.producer.EntityEntryProducer;
import org.sharks.storage.dao.CountryDao;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CountryServiceImp implements CountryService {
	
	@Inject
	private CountryDao dao;
	
	@Inject
	private CountryEntryProducer entryProducer;
	
	@Inject
	private MonikerService monikers;
	
	@Inject
	private EntityEntryProducer entityEntryProducer;
	
	@Override
	public CountryDetails get(String code) {
		Country country = dao.get(code);
		List<String> rfbs = monikers.getRfbsForCountry(code);
		return new CountryDetails(country.getCode(), 
				country.getUnName(), 
				convert(rfbs, entityEntryProducer),
				convert(country.getPoAs(), TO_POA_ENTRY));
	}

	@Override
	public List<CountryEntry> list(boolean onyWithPoas) {
		List<Country> countries = onyWithPoas?dao.listWithPoAs():dao.list();
		return convert(countries, entryProducer);
	}

}
