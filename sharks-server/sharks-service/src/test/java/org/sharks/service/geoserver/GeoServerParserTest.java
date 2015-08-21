/**
 * 
 */
package org.sharks.service.geoserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.sharks.service.util.TestUtils.getResource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sharks.service.geoserver.dto.SpeciesList;
import org.sharks.service.geoserver.rest.GeoServerParser;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class GeoServerParserTest {
	
	private static GeoServerParser parser;
	
	@BeforeClass
	public static void initParser() {
		parser = new GeoServerParser();
	}

	/**
	 * Test method for {@link org.sharks.service.geoserver.rest.GeoServerParser#parseSpeciesList(java.lang.String)}.
	 */
	@Test
	public void testParseSpeciesList() {
		String content = getResource("/geoserver/specieslist.xml");
		SpeciesList list = parser.parseSpeciesList(content);
		
		assertNotNull(list);
		assertEquals(791, list.getItems().size());
	}

}
