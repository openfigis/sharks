package org.sharks.storage.dao;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.domain.Species;

@RunWith(CdiRunner.class)
@AdditionalClasses(Producers.class)
public class SpeciesDaoTest {

	@Inject
	SpeciesDao dao;

	@Test
	public void experiment() throws SQLException {
		List<Species> species = dao.list();
		for (Species s:species) System.out.println(s);
	}
	
}
