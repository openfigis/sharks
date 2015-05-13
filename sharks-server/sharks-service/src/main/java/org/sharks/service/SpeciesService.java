/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.service.dto.SpeciesDetails;
import org.sharks.service.dto.SpeciesEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface SpeciesService {

	/**
	 * Returns the {@link SpeciesDetails} for the species with the specified 3alpha code.
	 * @param alphacode the species alpha code.
	 * @return the found {@link SpeciesDetails}, otherwise <code>null</code> if the species has not be found.
	 */
	SpeciesDetails getSpecies(String alphacode);

	List<SpeciesEntry> list(boolean onlyWithMeasure);

}
