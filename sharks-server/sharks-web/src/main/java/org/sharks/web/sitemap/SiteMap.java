/**
 * 
 */
package org.sharks.web.sitemap;

import java.net.URL;
import java.util.List;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
public class SiteMap {
	
	private final URL baseUrl;
	private final List<URL> urls;
}
