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

import org.sharks.service.MeasuresService;
import org.sharks.service.dto.EntityMeasures;
import org.sharks.service.dto.MeasureDetails;
import org.sharks.storage.dao.MeasuresDao;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MeasuresServiceImpl implements MeasuresService {
	
	@Inject
	private MeasuresDao dao;

	@Override
	public List<MeasureDetails> list() {
		return dao.list().stream().map(m->toDetails(m)).collect(Collectors.toList());
	}

	@Override
	public MeasureDetails get(String code) {
		return toDetails(dao.get(code));
	}
	
	private MeasureDetails toDetails(Measure measure) {
		if (measure == null) return null;
		return new MeasureDetails(measure.getCode(), 
				measure.getSymbol(), 
				measure.getTitle(), 
				measure.getMeasureYear(), 
				measure.getInformationSources());
	}

	@Override
	public List<EntityMeasures> measureForSpeciesByEntity(List<String> speciesAlphaCodes) {
		
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
								Collectors.mapping(m->toDetails(m), Collectors.toList())
				)).entrySet().stream()
				//map entry to EntityMeasures
				.map((Entry<MgmtEntity, List<MeasureDetails>> e) -> new EntityMeasures(e.getKey(), e.getValue()))
				.collect(Collectors.toList());

		
		return entityMeasures;
	}

}
