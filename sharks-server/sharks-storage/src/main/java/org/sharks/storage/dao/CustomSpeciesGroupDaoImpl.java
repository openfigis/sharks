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
public class CustomSpeciesGroupDaoImpl extends AbstractDao<CustomSpeciesGrp, Long> implements CustomSpeciesGroupDao {

	@Inject
	public CustomSpeciesGroupDaoImpl(EntityManagerFactory emf) {
		super(emf, CustomSpeciesGrp.class);
	}
}
