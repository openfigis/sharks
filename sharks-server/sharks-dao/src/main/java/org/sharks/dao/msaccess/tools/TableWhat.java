package org.sharks.dao.msaccess.tools;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.sharks.dao.msaccess.config.MsAccessConfiguration;

/**
 * Reads from the MS Access DB.
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public class TableWhat {
	@Inject
	MsAccessConfiguration c;

	@Inject
	private TableReader tableReader;

	public List<RecordCollection> readObjects() {

		List<RecordCollection> tableList = new ArrayList<RecordCollection>();

		for (Class<?> clazz : c.getTables()) {
			RecordCollection table = tableReader.read(clazz);
			tableList.add(table);
		}
		return tableList;
	}

}
