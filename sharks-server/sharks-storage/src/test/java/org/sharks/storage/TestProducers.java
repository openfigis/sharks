/**
 * 
 */
package org.sharks.storage;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.sharks.storage.dao.SharksStorageException;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
public class TestProducers {

	@Produces
	public static EntityManagerFactory getEntityManagerFactory() {

		try {

			Map<String, String> properties = new HashMap<String, String>();
			System.out.println("LOCATION **************************** "+TestConstants.TEST_DB_LOCATION);
			properties.put("hibernate.hikari.dataSource.accessPath", TestConstants.TEST_DB_LOCATION);

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sharks-storage", properties);

			return emf;

		} catch (Exception e) {
			throw new SharksStorageException("EntityManagerFactory creation failed", e);
		}
	}

	void closeEntityManagerFactory(@Disposes EntityManagerFactory emf) {
		emf.close();
	}

}
