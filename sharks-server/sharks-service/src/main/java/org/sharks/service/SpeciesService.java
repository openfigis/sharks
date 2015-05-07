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

	List<Species> list(boolean onlyWithMeasure);

	SpeciesDetails getSpecies(String alphacode);
}
