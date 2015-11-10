/**
 * 
 */
package org.sharks.service.producer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.sharks.service.producer.EntryProducers.TO_ENTITY_DOC;
import static org.sharks.service.producer.EntryProducers.TO_MEASURE_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.EntityDocument;
import org.sharks.service.dto.MeasureEntry;
import org.sharks.service.test.util.TestModelUtils;
import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;

public class EntryProducersTest {

	String acronym = "acronym";
	String name = "name";
	long type = 1l;

	@Test
	public void TO_ENTITY_DOC() {
		List<EntityDetails> mes = new ArrayList<EntityDetails>();
		mes.add(new EntityDetails(0l, "", "", 0l, "", "", "", null, null, null));
		mes.add(new EntityDetails(0l, "", "", 0l, "", "", "", null, null, null));

		List<EntityDocument> memberships = convert(mes, TO_ENTITY_DOC);
		assertEquals(memberships.size(), 2);
	}

	@Test
	public void TO_MEASURE_ENTRY() {
		InformationSource is = TestModelUtils.buildInformationSource(9);

		MgmtEntity me = TestModelUtils.buildEntity(5l, "44", is);
		me.setAcronym(acronym);

		Measure m = TestModelUtils.buildMeasure(4l, "AAA");
		m.setMgmtEntity(me);

		List<Measure> mes = new ArrayList<Measure>();
		mes.add(m);

		List<MeasureEntry> memberships = convert(mes, TO_MEASURE_ENTRY);
		for (MeasureEntry measureEntry : memberships) {
			assertNotNull(measureEntry.getEntityAcronym());
		}
		assertEquals(memberships.size(), 1);
	}

}
