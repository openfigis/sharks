/**
 * 
 */
package org.sharks.storage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.sharks.storage.domain.CustomSpeciesGrp;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CustomSpeciesGroupDao extends AbstractDao<CustomSpeciesGrp, Long> {

	@Inject
	public CustomSpeciesGroupDao(EntityManagerFactory emf) {
		super(emf, CustomSpeciesGrp.class);
	}
}
