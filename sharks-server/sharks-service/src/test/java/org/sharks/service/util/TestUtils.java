/**
 * 
 */
package org.sharks.service.util;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.sharks.service.moniker.MonikersParserTest;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestUtils {
	
	
	public static String getResource(String name) {
		try {
			InputStream file = MonikersParserTest.class.getResourceAsStream(name);
			return IOUtils.toString(file);
		} catch(Exception e) {
			throw new RuntimeException("Failed reading resource "+name);
		}
	}

}
