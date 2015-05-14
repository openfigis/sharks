/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.MeasureService;
import org.sharks.service.dto.MeasureEntry;
import org.sharks.storage.dao.MeasureDaoImpl;
import org.sharks.storage.domain.Measure;

import static org.sharks.service.producer.EntryProducers.*;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MeasureServiceImpl implements MeasureService {
	
	@Inject
	private MeasureDaoImpl dao;

	@Override
	public List<MeasureEntry> list() {
		return convert(dao.list(),TO_MEASURE_ENTRY);
	}

	@Override
	public Measure get(Long code) {
		return dao.get(code);
	}
}
