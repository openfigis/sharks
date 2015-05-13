/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.SpeciesDetails;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface SpeciesService {

	SpeciesDetails getSpecies(String alphacode);

	List<Species> list(boolean onlyWithMeasure);

}
