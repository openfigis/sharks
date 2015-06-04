package org.sharks.storage.dao;

import java.util.List;

import org.sharks.storage.domain.Measure;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface MeasureDao {

	public List<Measure> list();

}