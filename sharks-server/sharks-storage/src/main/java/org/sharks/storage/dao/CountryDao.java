package org.sharks.storage.dao;

import java.util.List;

import org.sharks.storage.domain.Country;

public interface CountryDao {

	/**
	 * Lists all the countries with almost one PoA.
	 * @return the list of found countries.
	 */
	public List<Country> listWithPoAs();

	public Country get(String code);

	public List<Country> list();

}