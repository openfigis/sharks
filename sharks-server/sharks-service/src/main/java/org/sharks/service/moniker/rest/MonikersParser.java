/**
 * 
 */
package org.sharks.service.moniker.rest;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.sharks.service.moniker.dto.FaoLexFiDocument;
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
			JAXBContext context = JAXBContext.newInstance(MonikerResponse.class, RfbEntry.class, FigisDoc.class, FaoLexFiDocument.class);
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
	
	public static class DateAdapter extends XmlAdapter<String, Date> {

	    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

	    @Override
	    public String marshal(Date v) throws Exception {
	        return dateFormat.format(v);
	    }

	    @Override
	    public Date unmarshal(String v) throws Exception {
	        return dateFormat.parse(v);
	    }

	}
}
