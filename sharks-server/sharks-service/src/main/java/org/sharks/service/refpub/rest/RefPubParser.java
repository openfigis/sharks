/**
 * 
 */
package org.sharks.service.refpub.rest;

import java.io.InputStream;

import javax.inject.Inject;
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
	
	@Inject
	private RefPubConverter converter;
	
	private Unmarshaller unmarshaller;
	
	public RefPubParser() {
		try {
			JAXBContext context = JAXBContext.newInstance(RefPubConcept.class);
			unmarshaller = context.createUnmarshaller();
		} catch(Exception e) {
			throw new RuntimeException("Error initializing JAXB", e);
		}
	}
	
	public RefPubCountry parseCountry(InputStream is) {
		RefPubConcept concept = parseConcept(is);
		return converter.toCountry(concept);
	}
	
	public RefPubSpecies parseSpecies(InputStream is) {
		RefPubConcept concept = parseConcept(is);
		return converter.toSpecies(concept);
	}
	
	private RefPubConcept parseConcept(InputStream is) {
		try {
			return (RefPubConcept) unmarshaller.unmarshal(is);
		} catch(Exception e) {
			throw new RuntimeException("Error parsing concept", e);
		}
	}

}
