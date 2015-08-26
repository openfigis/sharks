/**
 * 
 */
package org.sharks.service.test.util;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestUtils {
	
	public static String getResource(String name) {
		try {
			InputStream file = TestUtils.class.getResourceAsStream(name);
			return IOUtils.toString(file);
		} catch(Exception e) {
			throw new RuntimeException("Failed reading resource "+name);
		}
	}
	
	public static String getResourcePath(String name) {
		try {
			return TestUtils.class.getResource(name).getPath();
		} catch(Exception e) {
			throw new RuntimeException("Failed getting resource path with name: "+name);
		}
	}

}
