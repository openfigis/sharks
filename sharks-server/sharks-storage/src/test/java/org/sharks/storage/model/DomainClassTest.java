/**
 * 
 */
package org.sharks.storage.model;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.sharks.storage.TestConstants;
import org.sharks.storage.TestProducers;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(Parameterized.class)
public class DomainClassTest {
	
	private static EntityManagerFactory emf;
	
	@BeforeClass
	public static void emfSetup() throws Exception {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		emf = TestProducers.getEntityManagerFactory();
	}
	
	@Parameters(name= "{0}")
	public static Collection<Object[]> getDomainClasses() {
		Reflections reflections = new Reflections(TestConstants.DOMAIN_PACKAGE, new SubTypesScanner(false), new TypeAnnotationsScanner());
		Collection<Object[]> classes = new ArrayList<Object[]>();
		for (Class<? extends Object> dclass:reflections.getTypesAnnotatedWith(Entity.class)) {
			
			classes.add(new Object[]{dclass});
		}
		return classes;
	}

	@AfterClass
	public static void closeEmf() throws SQLException {
		if (emf!=null) emf.close();
	}
	
	private Class<Object> domainClass;
	
	public DomainClassTest(Class<Object> domainClass) {
		this.domainClass = domainClass;
	}
	
	@Test
	public void testListing() {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(domainClass);
		Root<Object> rootEntry = cq.from(domainClass);
		CriteriaQuery<Object> all = cq.select(rootEntry);
		TypedQuery<Object> allQuery = em.createQuery(all);
		List<Object> items = allQuery.getResultList();
		assertNotNull(items);
	}
}
