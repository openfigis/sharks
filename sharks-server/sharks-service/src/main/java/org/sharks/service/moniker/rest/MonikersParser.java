/**
 * 
 */
package org.sharks.service.moniker.rest;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.sharks.service.moniker.dto.FigisDoc;
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
			JAXBContext context = JAXBContext.newInstance(MonikerResponse.class, RfbEntry.class, FigisDoc.class);
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
	
	public FigisDoc parseFigisDoc(String content) {
		try {
			return (FigisDoc) unmarshaller.unmarshal(new StringReader(content));
		} catch(Exception e) {
			throw new RuntimeException("Error parsing FigisDoc", e);
		}
	}
}
