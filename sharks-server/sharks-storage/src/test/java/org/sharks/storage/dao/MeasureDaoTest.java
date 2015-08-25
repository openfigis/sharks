package org.sharks.storage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.TestProducers;
import org.sharks.storage.domain.Measure;

@RunWith(CdiRunner.class)
@AdditionalClasses({TestProducers.class, MeasureDaoImpl.class})
public class MeasureDaoTest {

	@Inject
	MeasureDao dao;

	@Test
	public void testReplacementLoops() {
		Measure measureA = dao.get(3l);
		assertNotNull(measureA);
		assertNotNull(measureA.getReplaces());
		assertEquals(measureA.getReplaces().getCode(), new Long(4l));
		assertNotNull(measureA.getReplacedBy());
		assertEquals(measureA.getReplacedBy().getCode(), new Long(4l));
		
		Measure measureB = dao.get(4l);
		assertNotNull(measureB);
		assertNotNull(measureB.getReplaces());
		assertEquals(measureB.getReplaces().getCode(), new Long(3l));
		assertNotNull(measureB.getReplacedBy());
		assertEquals(measureB.getReplacedBy().getCode(), new Long(3l));

	}
	
	
}
