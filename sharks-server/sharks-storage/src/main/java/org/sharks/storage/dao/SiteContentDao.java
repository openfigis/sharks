/**
 * 
 */
package org.sharks.storage.dao;

import java.util.HashMap;
import java.util.Map;

import org.sharks.storage.domain.SiteContent;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SiteContentDao {
	
	private static final Map<String, SiteContent> contents = new HashMap<String, SiteContent>();
	
	static {
		contents.put("FOOTER_1", new SiteContent(
				"FOOTER_1",
				"The database provides a collection of information on instruments for conservation and management of sharks. "
				+ "It is developed for users to search and get information from different management entities."));
		
		contents.put("FOOTER_2", new SiteContent(
				"FOOTER_2",
				"Users can search documents by species, RFBs and country. "
				+ "Through selection of a species, they can see the list of measures issued by RFBs for the particular species. "
				+ "Through selection of a RFB, they can see the list of measures issued by the selected RFB. "
				+ "Through selection of a country, they can see the list of the country’s Plan of Action and legislation."));
	}
	
	
	public SiteContent get(String keyword) {
		return contents.get(keyword);
	}
}
