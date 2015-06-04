/**
 * 
 */
package org.sharks.storage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.sharks.storage.domain.Measure;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MeasureDaoImpl extends AbstractDao<Measure, Long> implements MeasureDao {

	@Inject
	public MeasureDaoImpl(EntityManagerFactory emf) {
		super(emf, Measure.class);
	}
}
