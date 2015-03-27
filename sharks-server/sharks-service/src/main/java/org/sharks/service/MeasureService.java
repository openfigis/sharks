/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.EntityMeasures;
import org.sharks.service.dto.MeasureDetails;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface MeasureService {

	List<MeasureDetails> list();

	MeasureDetails get(String code);
	
	List<EntityMeasures> measuresForSpeciesByEntity(List<String> speciesAlphaCodes);
	
	List<MeasureDetails> measuresForManagementEntity(String acronym);
	
}
