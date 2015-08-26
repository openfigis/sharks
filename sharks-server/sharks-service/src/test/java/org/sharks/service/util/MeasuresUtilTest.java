package org.sharks.service.util;

import static org.junit.Assert.*;
import static org.sharks.service.test.util.TestModelUtils.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.sharks.storage.domain.Measure;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MeasuresUtilTest {


	@Test
	public void testFilterReplacedAndHiddenMeasures() {
		Measure measure = buildMeasure(0, "");
		measure.setHide(null);
		
		Measure replacedMeasure = buildMeasure(1, "");
		replacedMeasure.setReplacedBy(measure);
		
		Measure hiddenMeasure = buildMeasure(2, "");
		hiddenMeasure.setHide(true);
		
		
		Collection<Measure> filtered = MeasuresUtil.filterReplacedAndHiddenMeasures(Arrays.asList(measure,replacedMeasure,hiddenMeasure));
		assertEquals(1, filtered.size());
		assertEquals(new Long(0), filtered.iterator().next().getCode());
	}

}
