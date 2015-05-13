package org.sharks.storage.dao;

import java.util.List;

import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface SpeciesDao {

	/**
	 * Retrieves all the {@link Species} with almost a {@link Measure} related.
	 * @return the list of {@link Species}.
	 */
	public List<Species> listWithMeasures();

	/**
	 * Returns the {@link Species} with the specified alpha code.
	 * @param alphaCode the species alpha code.
	 * @return the found {@link Species}, <code>null</code> otherwise.
	 */
	public Species getByAlphaCode(String alphaCode);

	/**
	 * List all the species.
	 * @return
	 */
	public List<Species> list();

}