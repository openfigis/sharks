/**
 * 
 */
package org.sharks.service.refpub.rest;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.sharks.service.refpub.dto.RefPubConcept;
import org.sharks.service.refpub.dto.RefPubConverter;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class RefPubParser {
	
	private static final String EMPTY_CONCEPT = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><concept xmlns=\"http://www.fao.org/fi/refpub/webservice\"/>";
	
	private RefPubConverter converter;
	
	private Unmarshaller unmarshaller;
	
	public RefPubParser() {
		try {
			JAXBContext context = JAXBContext.newInstance(RefPubConcept.class);
			unmarshaller = context.createUnmarshaller();
		} catch(Exception e) {
			throw new RuntimeException("Error initializing JAXB", e);
		}
		
		converter = new RefPubConverter();
	}
	
	/**
	 * Parses the given content to a {@link RefPubCountry}.
	 * @param content the content to parse.
	 * @return the parsed {@link RefPubCountry}, <code>null</code> if the {@link RefPubConcept} is empty.
	 */
	public RefPubCountry parseCountry(String content) {
		if (isEmptyConcept(content)) return null;
		RefPubConcept concept = parseConcept(content);
		return converter.toCountry(concept);
	}
	
	/**
	 * Parses the given content to a {@link RefPubSpecies}.
	 * @param content the content to parse.
	 * @return the parsed {@link RefPubSpecies}, <code>null</code> if the {@link RefPubConcept} is empty.
	 */
	public RefPubSpecies parseSpecies(String content) {
		if (isEmptyConcept(content)) return null;
		RefPubConcept concept = parseConcept(content);
		return converter.toSpecies(concept);
	}
	
	private RefPubConcept parseConcept(String content) {
		try {
			return (RefPubConcept) unmarshaller.unmarshal(new StringReader(content));
		} catch(Exception e) {
			throw new RuntimeException("Error parsing concept", e);
		}
	}
	
	private boolean isEmptyConcept(String content) {
		return EMPTY_CONCEPT.equals(content);
	}

}
