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
import org.sharks.service.dto.EntityDocument;
import org.sharks.service.dto.EntityEntry;

public class EntryProducersTest {

	String acronym = "acronym";
	String name = "name";
	long type = 1l;

	@Test
	public void TO_ENTITY_DOC() {
		List<EntityEntry> mes = new ArrayList<EntityEntry>();
		mes.add(new EntityEntry(acronym, name, type));
		mes.add(new EntityEntry(acronym, name, type));
		List<EntityDocument> memberships = convert(mes, TO_ENTITY_DOC);
		assertEquals(memberships.size(), 2);
	}

}
