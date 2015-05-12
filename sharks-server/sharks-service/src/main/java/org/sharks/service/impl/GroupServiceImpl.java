/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.sharks.service.GroupService;
import org.sharks.service.dto.GroupDetails;
import org.sharks.service.dto.GroupEntry;
import org.sharks.service.dto.SpeciesEntry;
import org.sharks.storage.dao.CustomSpeciesGroupDao;
import org.sharks.storage.domain.CustomSpeciesGrp;
import org.sharks.storage.domain.Species;

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
		return dao.list().stream().map(group->toEntry(group)).collect(Collectors.toList());
	}
	
	private GroupEntry toEntry(CustomSpeciesGrp group) {
		return new GroupEntry(group.getCode(), 
				group.getCustomSpeciesGrp());
	}
	
	private GroupDetails toDetails(CustomSpeciesGrp group) {
		if (group==null) return null;
		List<SpeciesEntry> species = group.getSpecies().stream().map(sp->toSpeciesEntry(sp)).collect(Collectors.toList());
		return new GroupDetails(group.getCode(), group.getCustomSpeciesGrp(), species);
	}
	
	private SpeciesEntry toSpeciesEntry(Species species) {
		return new SpeciesEntry(species.getAlphaCode(), species.getAlphaCode(), species.getNameEn());
	}

}
