/**
 * 
 */
package org.sharks.service.http;

import java.net.URL;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface HttpClient {
	
	/**
	 * Gets the content from the specified URL.
	 * @param url the content URL.
	 * @return the content.
	 */
	public String get(URL url);

}
