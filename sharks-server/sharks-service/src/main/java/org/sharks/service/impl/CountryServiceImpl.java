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
import org.sharks.storage.dao.ManagementEntityDao;
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
				convert(country.getPoAs(), TO_POA_ENTRY));
		return details;
	}

	@Override
	public List<CountryEntry> list(boolean onyWithPoas) {
		
		//TODO filter only with POAS
		
		List<MgmtEntity> countries = onyWithPoas?dao.list(ManagementEntityDao.COUNTRY_TYPE):dao.list(ManagementEntityDao.COUNTRY_TYPE);
		return convert(countries, entryProducer);
	}

}
