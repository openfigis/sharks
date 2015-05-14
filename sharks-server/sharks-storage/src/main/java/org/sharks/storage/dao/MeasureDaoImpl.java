/**
 * 
 */
package org.sharks.storage.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MeasureDaoImpl extends AbstractDao<Measure, Long> implements MeasureDao {

	@Inject
	public MeasureDaoImpl(EntityManagerFactory emf) {
		super(emf, Measure.class);
	}
	
	@Override
	public List<Measure> listRelatedToManagementEntityAcronym(String acronym) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Measure> cq = cb.createQuery(type);
		Root<Measure> measure = cq.from(type);
		Join<Measure, MgmtEntity> entities = measure.join("mgmtEntity");
		cq.where(cb.equal(entities.get("acronym"), acronym));
		
		CriteriaQuery<Measure> all = cq.select(measure);
		TypedQuery<Measure> allQuery = em.createQuery(all);
		return allQuery.getResultList();
	}

}
