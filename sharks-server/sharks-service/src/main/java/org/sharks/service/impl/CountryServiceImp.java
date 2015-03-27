/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.CountryService;
import org.sharks.storage.dao.CountryDao;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CountryServiceImp implements CountryService {
	
	@Inject
	private CountryDao dao;

	@Override
	public List<Country> list() {
		return dao.list();
	}
}
