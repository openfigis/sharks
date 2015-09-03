/**
 * 
 */
package org.sharks.service.test.util;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.ServiceCacheManager.ServiceInfo;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestUtils {
	
	public static InputStream getResourceAsStream(String name) {
		try {
			return TestUtils.class.getResourceAsStream(name);
		} catch(Exception e) {
			throw new RuntimeException("Failed getting resource "+name);
		}
	}
	
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
	
	public static ServiceInfo getService(String name, ServiceType ... types) {
		return new ServiceInfo(name, types.length==0?ServiceType.INTERNAL:types[0]);
	}

}
