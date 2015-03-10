package org.sharks.dao.msaccess.tools;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.dao.msaccess.config.MsAccessConfiguration;
import org.sharks.dao.msaccess.config.MsAccessConnectionProducer;
import org.sharks.dao.table.refSpecies;

@RunWith(CdiRunner.class)
public class TableReaderTest {

	@Inject
	TableReader tableReader;

	@Inject
	MsAccessConfiguration c;

	@Inject
	MsAccessConnectionProducer cp;

	@Test
	public void testRead() throws SQLException {

		RecordCollection table = tableReader.read(c.getTables()[0]);
		assertEquals(2, table.getObjectList().size());
		assertEquals(refSpecies.class, table.getClazz());
		List<Object> records = table.getObjectList();
		for (Object record : records) {
			refSpecies refArea = (refSpecies) record;
			System.out.println(refArea.getCdSpecies());
		}

	}

}
