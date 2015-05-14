package org.sharks.storage.dao;

import java.util.List;

import org.sharks.storage.domain.CustomSpeciesGrp;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CustomSpeciesGroupDao {

	CustomSpeciesGrp get(Long code);

	List<CustomSpeciesGrp> list();

}