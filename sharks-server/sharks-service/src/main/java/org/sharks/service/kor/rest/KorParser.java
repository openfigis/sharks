/**
 * 
 */
package org.sharks.service.kor.rest;

import java.io.InputStream;
import java.io.StringReader;

import javax.inject.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.sharks.service.kor.dto.KorMappingList;
import org.sharks.service.kor.dto.KorResource;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class KorParser {

	private Unmarshaller unmarshaller;

	public KorParser() {
		try {
			JAXBContext context = JAXBContext.newInstance(KorResource.class, KorMappingList.class);
			unmarshaller = context.createUnmarshaller();
		} catch(Exception e) {
			throw new RuntimeException("Error initializing JAXB", e);
		}
	}

	public KorResource parseResource(String content) {
		try {
			return (KorResource) unmarshaller.unmarshal(new StringReader(content));
		} catch(Exception e) {
			throw new RuntimeException("Error parsing KOR resource", e);
		}
	}
	
	public KorMappingList parseMappings(InputStream source) {
		try {
			return (KorMappingList) unmarshaller.unmarshal(source);
		} catch(Exception e) {
			throw new RuntimeException("Error parsing KOR mappings", e);
		}
	}

}
