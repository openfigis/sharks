/**
 * 
 */
package org.sharks.service.kor;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.Cached;
import org.sharks.service.kor.dto.KorMappingList;
import org.sharks.service.kor.dto.KorMappingList.KorMapping;
import org.sharks.service.kor.dto.KorResource;
import org.sharks.service.kor.rest.KorParser;
import org.sharks.service.kor.rest.KorRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@Service(name="kor",type=ServiceType.EXTERNAL)
public class KorServiceImpl implements KorService {

	private Map<String, String> mappings;

	@Inject
	private KorParser parser;
	
	@Inject
	private KorRestClient restClient;

	@Override @Cached("resources")
	public KorResource getResource(String acronym) {

		String id = getId(acronym);
		if (id == null) {
			log.error("Unknow resource with acronym {}",acronym);
			return null;
		}
		try {
			return restClient.getResource(id);
		} catch(Exception e){
			log.error("Failed retrieving resource id {} for acronym {}", id, acronym);
			return null;
		}
	}

	private String getId(String acronym) {
		if (mappings == null) loadMappings();
		return mappings.get(acronym);
	}

	private void loadMappings() {
		InputStream stream = KorServiceImpl.class.getResourceAsStream("mappings.xml");
		KorMappingList mappingList = parser.parseMappings(stream);
		mappings = new HashMap<String, String>();
		for (KorMapping mapping:mappingList.getMappings()) {
			mappings.put(mapping.getAcronym(), mapping.getId());
		}

	}

}
