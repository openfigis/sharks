/**
 * 
 */
package org.sharks.service.cites.rest;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.sharks.service.cites.dto.CitesCountry;
import org.sharks.service.cites.dto.CitesParties;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CitesParser {

	private Unmarshaller unmarshaller;

	public CitesParser() {
		try {
			JAXBContext context = JAXBContext.newInstance(CitesParties.class, CitesCountry.class);
			unmarshaller = context.createUnmarshaller();
		} catch(Exception e) {
			throw new RuntimeException("Error initializing JAXB", e);
		}
	}

	public CitesParties parseParties(String content) {
		try {
			return (CitesParties) unmarshaller.unmarshal(new StringReader(content));
		} catch(Exception e) {
			throw new RuntimeException("Error parsing cites countries", e);
		}
	}

}
