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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	@Override
	public List<InformationSource> listRelatedToEntity(Long entityCode, Long ... types) {
		
		EntityManager entityManager = emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<InformationSource> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<InformationSource> sources = criteriaQuery.from(type);
		
		
		Predicate wherePredicate = criteriaBuilder.equal(sources.get("mgmtEntity"),entityCode);
		
		if (types.length>0) {
			wherePredicate = criteriaBuilder.and(wherePredicate, sources.get("informationType").in((Object[])types));
		}
		
		criteriaQuery.where(wherePredicate);
		
		TypedQuery<InformationSource> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

}
