/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.sharks.service.MeasureService;
import org.sharks.service.dto.MeasureEntry;
import org.sharks.storage.dao.MeasureDao;
import org.sharks.storage.domain.Measure;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MeasureServiceImpl implements MeasureService {
	
	@Inject
	private MeasureDao dao;

	@Override
	public List<MeasureEntry> list() {
		return dao.list().stream().map(m->toEntry(m)).collect(Collectors.toList());
	}

	@Override
	public Measure get(Long code) {
		return dao.get(code);
	}
	
	private MeasureEntry toEntry(Measure measure) {
		if (measure == null) return null;
		return new MeasureEntry(measure.getCode(), 
				measure.getSymbol(), 
				measure.getTitle(), 
				measure.getDocumentType()!=null?measure.getDocumentType().getDescription():null,
				measure.getMeasureYear(), 
				measure.getBinding(),
				measure.getInformationSources());
	}
}
