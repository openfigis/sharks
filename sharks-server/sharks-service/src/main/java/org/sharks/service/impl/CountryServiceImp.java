/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_POA_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.Collections;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.CountryService;
import org.sharks.service.dto.CountryDetails;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.event.ApplicationEvent;
import org.sharks.service.producer.CountryEntryProducer;
import org.sharks.storage.dao.CountryDao;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class CountryServiceImp implements CountryService {
	
	@Inject
	private CountryDao dao;
	
	@Inject
	private CountryEntryProducer entryProducer;
	
	void cacheWarmup(@Observes ApplicationEvent.Startup startup) {
		log.trace("cache warmup");
		for (Country country:dao.listWithPoAs()) get(country.getCode());
		log.trace("cache warmup complete");
	}
	
	@Override
	public CountryDetails get(String code) {
		Country country = dao.get(code);
		return new CountryDetails(country.getCode(), 
				country.getUnName(), 
				Collections.emptyList(),
				convert(country.getPoAs(), TO_POA_ENTRY));
	}

	@Override
	public List<CountryEntry> list(boolean onyWithPoas) {
		List<Country> countries = onyWithPoas?dao.listWithPoAs():dao.list();
		return convert(countries, entryProducer);
	}

}
