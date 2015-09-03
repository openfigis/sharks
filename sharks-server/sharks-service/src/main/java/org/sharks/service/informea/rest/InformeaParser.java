/**
 * 
 */
package org.sharks.service.informea.rest;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.sharks.service.informea.dto.InformeaCountry;
import org.sharks.service.informea.dto.InformeaParties;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class InformeaParser {

	private Unmarshaller unmarshaller;

	public InformeaParser() {
		try {
			JAXBContext context = JAXBContext.newInstance(InformeaParties.class, InformeaCountry.class);
			unmarshaller = context.createUnmarshaller();
		} catch(Exception e) {
			throw new RuntimeException("Error initializing JAXB", e);
		}
	}

	public InformeaParties parseParties(String content) {
		try {
			return (InformeaParties) unmarshaller.unmarshal(new StringReader(content));
		} catch(Exception e) {
			throw new RuntimeException("Error parsing countries", e);
		}
	}

}
