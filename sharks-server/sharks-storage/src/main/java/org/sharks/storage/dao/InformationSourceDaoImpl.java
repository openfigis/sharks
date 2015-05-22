/**
 * 
 */
package org.sharks.storage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.sharks.storage.domain.InformationSource;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class InformationSourceDaoImpl extends AbstractDao<InformationSource, Long> implements InformationSourceDao {

	@Inject
	public InformationSourceDaoImpl(EntityManagerFactory emf) {
		super(emf, InformationSource.class);
	}

}
