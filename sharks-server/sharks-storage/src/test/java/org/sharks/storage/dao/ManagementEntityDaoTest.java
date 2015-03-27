package org.sharks.storage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.TestProducers;
import org.sharks.storage.domain.MgmtEntity;

@RunWith(CdiRunner.class)
@AdditionalClasses(TestProducers.class)
public class ManagementEntityDaoTest {

	@Inject
	ManagementEntityDao dao;

	/**
	 * Retrieves all the entity related to inexistent country, we expect no results.
	 */
	@Test
	public void testAllRelatedToCountry() {
		List<MgmtEntity> entities = dao.allRelatedToCountry("THIS COUNTRY DON'T EXISTS");
		assertNotNull(entities);
		assertEquals(0, entities.size());

	}

}
