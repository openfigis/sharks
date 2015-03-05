package org.sharks.dao.msaccess.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.sharks.dao.SharksException;

public class TableReader {

	private final GenericMapper mapper = new GenericMapper();

	@Inject
	Connection connection;

	public RecordCollection read(Class<?> clazz) {
		RecordCollection table = new RecordCollection();
		List<Object> list = new ArrayList<Object>();
		table.setClazz(clazz);
		table.setObjectList(list);
		ResultSet rs = getResultset(clazz.getSimpleName());
		try {
			while (rs.next()) {
				Object o = mapper.generateObject(rs, clazz);
				list.add(o);
			}
		} catch (SQLException e) {
			throw new SharksException(e);
		}

		return table;
	}

	private ResultSet getResultset(String table) {
		Statement stmt = null;

		// SQL query command
		String SQL = "SELECT * FROM " + table;
		try {

			stmt = connection.createStatement();
			return stmt.executeQuery(SQL);
		} catch (SQLException e) {
			throw new SharksException(e);
		}
	}

}
