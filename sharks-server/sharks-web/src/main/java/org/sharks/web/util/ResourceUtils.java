/**
 * 
 */
package org.sharks.web.util;

import javax.ws.rs.WebApplicationException;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ResourceUtils {
	
	public static <T> T checkNotFound(T item) {
		if (item == null) throw new WebApplicationException(404);
		return item;
	}

}
