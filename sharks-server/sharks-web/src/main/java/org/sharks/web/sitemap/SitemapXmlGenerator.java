/**
 * 
 */
package org.sharks.web.sitemap;

import java.net.URL;
import java.util.Date;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SitemapXmlGenerator {
	
	private static final ChangeFreq frequency = ChangeFreq.DAILY;
	
	public String toXml(Sitemap siteMap) {
		WebSitemapGenerator generator = new WebSitemapGenerator(siteMap.getBaseUrl());
		
		for (URL url:siteMap.getUrls()) addUrl(generator, url);
		
		return toXml(generator);
	}
	
	private void addUrl(WebSitemapGenerator generator, URL url) {
		WebSitemapUrl webSiteUrl = new WebSitemapUrl
				.Options(url)
			    .lastMod(new Date())
			    .priority(1.0)
			    .changeFreq(frequency)
			    .build();
		generator.addUrl(webSiteUrl);
	}
	
	private String toXml(WebSitemapGenerator generator)
	{
		StringBuilder sb = new StringBuilder();
		for (String line:generator.writeAsStrings()) sb.append(line);
		return sb.toString();
	}
}
