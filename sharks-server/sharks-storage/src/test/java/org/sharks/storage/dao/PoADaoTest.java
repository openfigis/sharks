package org.sharks.storage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.TestProducers;
import org.sharks.storage.domain.PoA;

@RunWith(CdiRunner.class)
@AdditionalClasses(TestProducers.class)
public class PoADaoTest {

	
	@Inject
	PoADao dao;

	/**
	 * Retrieves all the PoA related to species ALB, we expect PoA 4 as result.
	 */
	@Test
	public void testAllRelatedToSpeciesAlphaCode() {
		List<PoA> poas = dao.allRelatedToCountry("ALB");
		assertNotNull(poas);
		assertEquals(1, poas.size());

		List<Long> ids = poas.stream().map(PoA::getCode).collect(Collectors.toList());

		assertTrue(ids.contains(4l));
	}

}
