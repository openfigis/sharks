/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.CountryComplementService;
import org.sharks.service.CountryService;
import org.sharks.service.dto.CountryEntry;
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
	private CountryComplementService complementService;

	@Override
	public List<CountryEntry> list(boolean onyWithPoas) {
		List<Country> countries = onyWithPoas?dao.listWithPoAs():dao.list();
		return complementService.complement(countries);
	}
}
