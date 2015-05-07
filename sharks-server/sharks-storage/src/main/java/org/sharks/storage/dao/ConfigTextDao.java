/**
 * 
 */
package org.sharks.storage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.sharks.storage.domain.ConfigText;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ConfigTextDao extends AbstractDao<ConfigText, Long>{
	
	@Inject
	public ConfigTextDao(EntityManagerFactory emf) {
		super(emf, ConfigText.class);
	}
	
	public ConfigText getByKeyword(String keyword) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ConfigText> cq = cb.createQuery(type);
        Root<ConfigText> rootEntry = cq.from(type);
        CriteriaQuery<ConfigText> all = cq.select(rootEntry);
        cq.where(cb.equal(rootEntry.get("cdMnemonicKey"), keyword));
        
        TypedQuery<ConfigText> allQuery = em.createQuery(all);
        return allQuery.getSingleResult();
	}
}
