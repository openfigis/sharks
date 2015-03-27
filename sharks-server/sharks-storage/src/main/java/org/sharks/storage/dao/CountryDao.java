/**
 * 
 */
package org.sharks.storage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CountryDao extends AbstractDao<Country> {

	@Inject
	public CountryDao(EntityManagerFactory emf) {
		super(emf, Country.class);
	}

}
