package org.sharks.dao.msaccess.tools.jackcess;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.dao.msaccess.config.MsAccessConfiguration;
import org.sharks.dao.msaccess.tools.MsAccessConnectionProvider;
import org.sharks.dao.msaccess.tools.RecordCollection;
import org.sharks.dao.msaccess.tools.TableReader;

@RunWith(CdiRunner.class)
public class TableReaderTest {

	@Inject
	TableReader tableReader;

	@Inject
	MsAccessConfiguration c;

	@Inject
	MsAccessConnectionProvider cp;

	@Test
	public void experiment() throws SQLException {
		// /"jdbc:ucanaccess://C:/__tmp/test/zzz.accdb");
		String url = "jdbc:ucanaccess://" + c.getDbLocation();
		// String url = "jdbc:ucanaccess://" + "C:/Users/vaningen/git/sharks/sharks-server/sharks-db/Sharks.accdb";
		// String url = "jdbc:ucanaccess://" + "C:/Users/vaningen/git/sharks/sharks-server/sharks-db/experiment.accdb";

		System.out.println(url);
		Connection conn = DriverManager.getConnection(url);
		// Connection conn = DriverManager.getConnection(url, "sa", "");

		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("SELECT cdArea FROM refArea ");
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
	}

	@Test
	public void testRead() throws SQLException {

		tableReader.setConnection(cp.getConnection());

		RecordCollection table = tableReader.read(c.getTables()[0]);
		assertEquals(19, table.getObjectList().size());

	}

}
