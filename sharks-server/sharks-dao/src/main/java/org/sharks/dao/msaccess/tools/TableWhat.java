package org.sharks.dao.msaccess.tools;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.sharks.dao.table.refArea;

/**
 * Reads from the MS Access DB.
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public class TableWhat {

	@Inject
	private TableReader tableReader;

	private final Class<?>[] tables = { refArea.class };

	public List<RecordCollection> readObjects() {

		List<RecordCollection> tableList = new ArrayList<RecordCollection>();

		for (Class<?> clazz : tables) {
			RecordCollection table = tableReader.read(clazz);
			tableList.add(table);
		}
		return tableList;
	}

	public Class<?>[] getTables() {
		return tables;
	}

}
