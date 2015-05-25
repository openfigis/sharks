/**
 * 
 */
package org.sharks.service.geoserver.rest;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.sharks.service.geoserver.dto.SpeciesItem;
import org.sharks.service.geoserver.dto.SpeciesList;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class GeoServerParser {
	
	private Unmarshaller unmarshaller;
	
	public GeoServerParser() {
		try {
			JAXBContext context = JAXBContext.newInstance(SpeciesList.class, SpeciesItem.class);
			unmarshaller = context.createUnmarshaller();
		} catch(Exception e) {
			throw new RuntimeException("Error initializing JAXB", e);
		}
	}
	
	public SpeciesList parseSpeciesList(String content) {
		try {
			return (SpeciesList) unmarshaller.unmarshal(new StringReader(content));
		} catch(Exception e) {
			throw new RuntimeException("Error parsing concept", e);
		}
	}

}
