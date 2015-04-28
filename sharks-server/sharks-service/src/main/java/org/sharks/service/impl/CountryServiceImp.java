/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.CountryComplementService;
import org.sharks.service.CountryService;
import org.sharks.service.dto.CountryDetails;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.event.ApplicationEvent;
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
	private CountryComplementService complementService;
	
	void cacheWarmup(@Observes ApplicationEvent.Startup startup) {
		log.trace("cache warmup");
		for (Country country:dao.listWithPoAs()) get(country.getCode());
		log.trace("cache warmup complete");
	}
	
	@Override
	public CountryDetails get(String code) {
		Country country = dao.get(code);
		return complementService.complement(country);
	}

	@Override
	public List<CountryEntry> list(boolean onyWithPoas) {
		List<Country> countries = onyWithPoas?dao.listWithPoAs():dao.list();
		return countries.stream().map(country->toEntry(country)).collect(Collectors.toList());
	}
	
	private CountryEntry toEntry(Country country) {
		return new CountryEntry(country.getCode(), country.getUnName());
	}


}
