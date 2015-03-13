/**
 * 
 */
package org.sharks.storage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SpeciesDao extends AbstractDao<Species> {

	@Inject
	public SpeciesDao(EntityManagerFactory emf) {
		super(emf, Species.class);
	}

}
