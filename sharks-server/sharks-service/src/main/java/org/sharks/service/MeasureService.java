/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.EntityMeasures;
import org.sharks.service.dto.MeasureEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface MeasureService {

	List<MeasureEntry> list();

	MeasureEntry get(String code);
	
	List<EntityMeasures> measuresForSpeciesByEntity(List<String> speciesAlphaCodes);
	
	List<MeasureEntry> measuresForManagementEntity(String acronym);
	
}
