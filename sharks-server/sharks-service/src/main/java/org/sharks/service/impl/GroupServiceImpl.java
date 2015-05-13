/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_GROUP_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_MEASURE_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_SPECIES_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.GroupService;
import org.sharks.service.dto.GroupDetails;
import org.sharks.service.dto.GroupEntry;
import org.sharks.storage.dao.CustomSpeciesGroupDao;
import org.sharks.storage.domain.CustomSpeciesGrp;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class GroupServiceImpl implements GroupService {
	
	@Inject
	private CustomSpeciesGroupDao dao;
	
	@Override
	public GroupDetails get(long code) {
		CustomSpeciesGrp group = dao.get(code);
		return toDetails(group);
	}

	@Override
	public List<GroupEntry> list() {
		return convert(dao.list(), TO_GROUP_ENTRY);
	}
	
	private GroupDetails toDetails(CustomSpeciesGrp group) {
		if (group==null) return null;
		return new GroupDetails(group.getCode(), group.getCustomSpeciesGrp(), 
				convert(group.getSpecies(), TO_SPECIES_ENTRY),
				convert(group.getMeasures(), TO_MEASURE_ENTRY));
	}

}
