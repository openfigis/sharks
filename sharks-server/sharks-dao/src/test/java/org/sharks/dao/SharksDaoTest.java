package org.sharks.dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.dao.model.Species;

@RunWith(CdiRunner.class)
public class SharksDaoTest {

	@Inject
	SharksDao dao;

	@Test
	public void experiment() throws SQLException {
		List<Species> species = dao.listAllSpecies();
		for (Species s:species) System.out.println(s);
	}

	
}
