/**
 * 
 */
package org.sharks.service.moniker.rest;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.sharks.service.moniker.dto.MonikerResponse;
import org.sharks.service.moniker.dto.RfbEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MonikersParser {
	
	private Unmarshaller unmarshaller;
	
	public MonikersParser() {
		try {
			JAXBContext context = JAXBContext.newInstance(MonikerResponse.class, RfbEntry.class);
			unmarshaller = context.createUnmarshaller();
		} catch(Exception e) {
			throw new RuntimeException("Error initializing JAXB", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> MonikerResponse<T> parseMonikerResponse(InputStream is) {
		try {
			return (MonikerResponse<T>) unmarshaller.unmarshal(is);
		} catch(Exception e) {
			throw new RuntimeException("Error parsing monikers response", e);
		}
	}

}
