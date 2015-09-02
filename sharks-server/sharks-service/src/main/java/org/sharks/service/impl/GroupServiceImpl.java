/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_GROUP_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_MEASURE_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;
import static org.sharks.service.util.MeasuresUtil.filterReplacedAndHiddenMeasures;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.GroupService;
import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.Cached;
import org.sharks.service.cache.warmer.CacheWarmer;
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
@Service(name="group",type=ServiceType.INTERNAL)
public class GroupServiceImpl implements GroupService {
	
	@Inject
	private CustomSpeciesGroupDao dao;
	
	@Inject
	private SpeciesEntryProducer speciesEntryProducer;
	
	@Override @Cached("get")
	public GroupDetails get(long code) {
		CustomSpeciesGrp group = dao.get(code);
		if (group==null) return null;
		return toDetails(group);
	}

	@Override @Cached(value="list", staticKey="key")
	public List<GroupEntry> list() {
		return convert(dao.list(), TO_GROUP_ENTRY);
	}
	
	private GroupDetails toDetails(CustomSpeciesGrp group) {
		return new GroupDetails(
				group.getCode(), 
				group.getCustomSpeciesGrp(),
				group.getAddInfo(),
				convert(group.getSpecies(), speciesEntryProducer),
				convert(filterReplacedAndHiddenMeasures(group.getMeasures()), TO_MEASURE_ENTRY));
	}

	public static final class GroupCachesWarmer implements CacheWarmer {

		@Inject
		GroupService service;
		
		@Override
		public void warmup() {
			for (GroupEntry entry:service.list()) {
				service.get(entry.getCode());
			}
		}
		
	}
}
