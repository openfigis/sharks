/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_GROUP_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_MEASURE_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.GroupService;
import org.sharks.service.dto.GroupDetails;
import org.sharks.service.dto.GroupEntry;
import org.sharks.service.producer.SpeciesEntryProducer;
import org.sharks.storage.dao.CustomSpeciesGroupDao;
import org.sharks.storage.domain.CustomSpeciesGrp;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class GroupServiceImpl implements GroupService {
	
	@Inject
	private CustomSpeciesGroupDao dao;
	
	@Inject
	private SpeciesEntryProducer speciesEntryProducer;
	
	@Override
	public GroupDetails get(long code) {
		CustomSpeciesGrp group = dao.get(code);
		if (group==null) return null;
		return toDetails(group);
	}

	@Override
	public List<GroupEntry> list() {
		return convert(dao.list(), TO_GROUP_ENTRY);
	}
	
	private GroupDetails toDetails(CustomSpeciesGrp group) {
		return new GroupDetails(
				group.getCode(), 
				group.getCustomSpeciesGrp(),
				group.getAddInfo(),
				convert(group.getSpecies(), speciesEntryProducer),
				convert(group.getMeasures(), TO_MEASURE_ENTRY));
	}

}
