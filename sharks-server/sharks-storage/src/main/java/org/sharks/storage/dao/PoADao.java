/**
 * 
 */
package org.sharks.storage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.sharks.storage.domain.PoA;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class PoADao extends AbstractDao<PoA, Long> {

	@Inject
	public PoADao(EntityManagerFactory emf) {
		super(emf, PoA.class);
	}

}
