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
import org.sharks.storage.domain.Measure;

@RunWith(CdiRunner.class)
@AdditionalClasses(TestProducers.class)
public class MeasureDaoTest {

	@Inject
	MeasureDao dao;
	
	/**
	 * Retrieves all the measures related to Management entity CCAMLR, we expect measures 3 and 4 as result.
	 */
	@Test
	public void testListRelatedToManagementEntityAcronym() {
		List<Measure> measures = dao.listRelatedToManagementEntityAcronym("CCAMLR");
		assertNotNull(measures);
		assertEquals(2, measures.size());

		List<Long> ids = measures.stream().map(Measure::getCode).collect(Collectors.toList());

		assertTrue(ids.contains(3l));
		assertTrue(ids.contains(4l));
	}
	
	@Test
	public void testListRelatedToManagementEntityAcronymWithWrongAcronym() {
		List<Measure> measures = dao.listRelatedToManagementEntityAcronym("NOT_EXISTS");
		assertNotNull(measures);
		assertTrue(measures.isEmpty());
	}

}
