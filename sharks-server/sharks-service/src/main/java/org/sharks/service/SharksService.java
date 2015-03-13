/**
 * 
 */
package org.sharks.service;

import java.util.List;

import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface SharksService {

	List<Species> listAllSpecies();

	Species getSpecies(String code);
}
