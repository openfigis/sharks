/**
 * 
 */
package org.sharks.web.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.dao.SharksDao;
import org.sharks.storage.dao.model.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
public class DaoTest {

	@Inject
	SharksDao dao;

	@Test
	public void experiment() throws SQLException {
		List<Species> species = dao.listAllSpecies();
		for (Species s:species) System.out.println(s);
	}

	
}
