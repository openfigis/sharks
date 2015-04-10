/**
 * 
 */
package org.sharks.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.sharks.service.MeasureService;
import org.sharks.service.dto.EntityMeasures;
import org.sharks.service.dto.MeasureEntry;
import org.sharks.storage.dao.MeasureDao;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;

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
				measure.getMeasureYear(), 
				measure.getInformationSources());
	}

	@Override
	public List<EntityMeasures> measuresForSpeciesByEntity(List<String> speciesAlphaCodes) {
		
		Set<Measure> measures = new HashSet<Measure>();
		
		for (String speciesAlphaCode:speciesAlphaCodes) {
			List<Measure> speciesMeasures = dao.allRelatedToSpeciesAlphaCode(speciesAlphaCode);
			measures.addAll(speciesMeasures);
		}

		List<EntityMeasures> entityMeasures = 
				measures.stream().collect(
						//group by entity
						Collectors.groupingBy(Measure::getMgmtEntity,
								//map Measure to MeasureDetails
								Collectors.mapping(m->toEntry(m), Collectors.toList())
				)).entrySet().stream()
				//map entry to EntityMeasures
				.map((Entry<MgmtEntity, List<MeasureEntry>> e) -> new EntityMeasures(e.getKey(), e.getValue()))
				.collect(Collectors.toList());

		
		return entityMeasures;
	}

	@Override
	public List<MeasureEntry> measuresForManagementEntity(String acronym) {
		return dao.allRelatedToManagementEntityAcronym(acronym) .stream().map(m->toEntry(m)).collect(Collectors.toList());
	}

}
