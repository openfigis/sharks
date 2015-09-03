package org.sharks.service.kor;

import static org.junit.Assert.*;
import static org.sharks.service.test.util.TestUtils.*;

import java.io.InputStream;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sharks.service.kor.dto.KorMappingList;
import org.sharks.service.kor.dto.KorResource;
import org.sharks.service.kor.rest.KorParser;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class KorParserTest {
	
	private static KorParser parser;
	
	@BeforeClass
	public static void setupContext() {
		parser = new KorParser();
	}

	@Test
	public void testParseResource() {
		String content = getResource("/kor/cites.xml");
		KorResource resource = parser.parseResource(content);
		
		assertNotNull(resource);
		assertNotNull(resource.getIconUrl());
		assertNotNull(resource.getUri());
	}
	
	@Test
	public void testParseMapppings() {
		InputStream content = getResourceAsStream("/kor/mappings.xml");
		KorMappingList mappings = parser.parseMappings(content);
		
		assertNotNull(mappings);
		assertNotNull(mappings.getMappings());
		assertFalse(mappings.getMappings().isEmpty());
	}


}
