/**
 * 
 */
package org.sharks.service.moniker.rest;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.sharks.service.moniker.dto.ErrorElement;
import org.sharks.service.moniker.dto.FaoLexFiDocument;
import org.sharks.service.moniker.dto.MonikerResponse;
import org.sharks.service.moniker.dto.Rfb;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MonikersParser {
	
	private Unmarshaller unmarshaller;
	
	public MonikersParser() {
		try {
			JAXBContext context = JAXBContext.newInstance(MonikerResponse.class, Rfb.class, FaoLexFiDocument.class, ErrorElement.class);
			unmarshaller = context.createUnmarshaller();
		} catch(Exception e) {
			throw new RuntimeException("Error initializing JAXB", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> MonikerResponse<T> parseMonikerResponse(String content) {
		try {
			return (MonikerResponse<T>) unmarshaller.unmarshal(new StringReader(content));
		} catch(Exception e) {
			throw new RuntimeException("Error parsing monikers response", e);
		}
	}
}
