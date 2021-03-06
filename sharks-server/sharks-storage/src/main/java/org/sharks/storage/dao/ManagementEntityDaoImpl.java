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
import javax.persistence.criteria.Subquery;

import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ManagementEntityDaoImpl extends AbstractDao<MgmtEntity, String> implements ManagementEntityDao {

	@Inject
	public ManagementEntityDaoImpl(EntityManagerFactory emf) {
		super(emf, MgmtEntity.class);
	}
	
	@Override
	public MgmtEntity getByAcronym(String acronym) {
		return getByField("acronym", acronym);
	}

	@Override
	public List<MgmtEntity> list(long type) {
		EntityManager entityManager = emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MgmtEntity> criteriaQuery = criteriaBuilder.createQuery(MgmtEntity.class);
        Root<MgmtEntity> rootEntry = criteriaQuery.from(MgmtEntity.class);
        CriteriaQuery<MgmtEntity> all = criteriaQuery.select(rootEntry);
        all.where(criteriaBuilder.equal(rootEntry.get("mgmtEntityType"), type));
		TypedQuery<MgmtEntity> query = entityManager.createQuery(all);
		return query.getResultList();
	}

	@Override
	public List<MgmtEntity> listCountries(boolean onlyWithPoAs, boolean onlyWithOthersSources) {
		EntityManager entityManager = emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MgmtEntity> criteriaQuery = criteriaBuilder.createQuery(MgmtEntity.class);
        Root<MgmtEntity> entity = criteriaQuery.from(MgmtEntity.class);
        CriteriaQuery<MgmtEntity> all = criteriaQuery.select(entity);
        
        Predicate where = criteriaBuilder.equal(entity.get("mgmtEntityType"), COUNTRY_TYPE);
        
        if (onlyWithPoAs) where = criteriaBuilder.and(where, criteriaBuilder.isNotEmpty(entity.get("poAs")));
       
        if (onlyWithOthersSources) {
        	Subquery<InformationSource> subQuery = criteriaQuery.subquery(InformationSource.class);
         	Root<InformationSource> sources = subQuery.from(InformationSource.class);
        	subQuery.select(sources.get("code"));
        	subQuery.where(criteriaBuilder.and(
        			criteriaBuilder.equal(sources.get("informationType"), InformationSourceDao.OTHER_TYPE),
        			criteriaBuilder.isMember(entity, sources.<List<MgmtEntity>>get("mgmtEntities"))
        			));
        	
        	where = criteriaBuilder.and(where, criteriaBuilder.exists(subQuery));
        }
        
        all.where(where);
        
		TypedQuery<MgmtEntity> query = entityManager.createQuery(all);
		return query.getResultList();
	}

	@Override
	public List<MgmtEntity> listRFMOsAndInstitutions(boolean onlyWithMeasures, boolean onlyWithOthersSources) {
		EntityManager entityManager = emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MgmtEntity> criteriaQuery = criteriaBuilder.createQuery(MgmtEntity.class);
        Root<MgmtEntity> entity = criteriaQuery.from(MgmtEntity.class);
        CriteriaQuery<MgmtEntity> all = criteriaQuery.select(entity);
        
        //type conditions
        Predicate where = criteriaBuilder.or(
        		criteriaBuilder.equal(entity.get("mgmtEntityType"), RFMO_TYPE),
        		criteriaBuilder.equal(entity.get("mgmtEntityType"), INSTITUTION_TYPE));
        
        if (onlyWithMeasures) where = criteriaBuilder.and(where, criteriaBuilder.isNotEmpty(entity.get("measures")));
       
        if (onlyWithOthersSources) {
        	Subquery<InformationSource> subQuery = criteriaQuery.subquery(InformationSource.class);
         	Root<InformationSource> sources = subQuery.from(InformationSource.class);
        	subQuery.select(sources.get("code"));
        	subQuery.where(criteriaBuilder.equal(sources.get("informationType"), InformationSourceDao.OTHER_TYPE));
        	where = criteriaBuilder.and(where, criteriaBuilder.exists(subQuery));
        }
        
        all.where(where);
        
		TypedQuery<MgmtEntity> query = entityManager.createQuery(all);
		return query.getResultList();
	}

}
