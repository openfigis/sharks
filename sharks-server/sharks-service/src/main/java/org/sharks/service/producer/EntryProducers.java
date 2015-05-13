/**
 * 
 */
package org.sharks.service.producer;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.sharks.service.dto.EntityEntry;
import org.sharks.service.dto.GroupEntry;
import org.sharks.service.dto.MeasureEntry;
import org.sharks.service.dto.PoAEntry;
import org.sharks.storage.domain.CustomSpeciesGrp;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.PoA;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class EntryProducers {
	
	public static <I,O> List<O> convert(Collection<I> input, EntryProducer<I, O> converter) {
		return input.stream().map(converter).collect(Collectors.toList());
	}
	
	public static final EntryProducer<Measure, MeasureEntry> TO_MEASURE_ENTRY = new AbstractEntryProducer<Measure, MeasureEntry>() {

		@Override
		public MeasureEntry produce(Measure measure) {
			return new MeasureEntry(measure.getCode(), 
					measure.getSymbol(), 
					measure.getTitle(), 
					measure.getDocumentType()!=null?measure.getDocumentType().getDescription():null,
					measure.getMeasureYear(), 
					measure.getBinding(),
					measure.getInformationSources());
		}
	};
	
	public static final EntryProducer<CustomSpeciesGrp, GroupEntry> TO_GROUP_ENTRY = new AbstractEntryProducer<CustomSpeciesGrp, GroupEntry>() {

		@Override
		public GroupEntry produce(CustomSpeciesGrp group) {
			return new GroupEntry(group.getCode(), 
					group.getCustomSpeciesGrp());
		}
	};
	
	public static final EntryProducer<PoA, PoAEntry> TO_POA_ENTRY = new AbstractEntryProducer<PoA, PoAEntry>() {

		@Override
		public PoAEntry produce(PoA poa) {
			return new PoAEntry(poa.getCode(), 
					poa.getTitle(), 
					poa.getPoAYear(), 
					poa.getPoAType(),
					poa.getStatus());
		}
	};
	
	public static final EntryProducer<MgmtEntity, EntityEntry> TO_ENTITY_ENTRY = new AbstractEntryProducer<MgmtEntity, EntityEntry>() {

		@Override
		public EntityEntry produce(MgmtEntity entity) {
			return new EntityEntry(entity.getCode(), 
					entity.getAcronym(), 
					entity.getMgmtEntityName());
		}

	};
	
	public static abstract class AbstractEntryProducer<I,O> implements EntryProducer<I,O> {

		@Override
		public O apply(I t) {
			return produce(t);
		}
	}
	
	public interface EntryProducer<I,O> extends Function<I, O>{
		public O produce(I item);
	}
}
