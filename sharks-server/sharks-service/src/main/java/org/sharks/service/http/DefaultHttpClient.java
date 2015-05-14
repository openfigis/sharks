/**
 * 
 */
package org.sharks.service.http;

import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class DefaultHttpClient implements HttpClient {

	@Override
	public String get(URL url) {
		try (InputStream is = url.openStream()) {
			String response = IOUtils.toString(is);
			return response;
		} catch(Exception e) {
			throw new RuntimeException("Failed retrieving content from URL: "+url, e);
		}
	}

}
