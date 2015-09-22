/**
 * 
 */
package org.sharks.service.producer;

import static org.junit.Assert.assertEquals;
import static org.sharks.service.producer.EntryProducers.TO_ENTITY_DOC;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.EntityDocument;

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

}
