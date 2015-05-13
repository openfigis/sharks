/**
 * 
 */
package org.sharks.service;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.sharks.service.dto.EntityEntry;
import org.sharks.service.dto.GroupEntry;
import org.sharks.service.dto.MeasureEntry;
import org.sharks.service.dto.PoAEntry;
import org.sharks.service.dto.SpeciesEntry;
import org.sharks.storage.domain.CustomSpeciesGrp;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.PoA;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class EntryConverters {
	
	public static <I,O> List<O> convert(Collection<I> input, EntryConverter<I, O> converter) {
		return input.stream().map(converter).collect(Collectors.toList());
	}
	
	public static final EntryConverter<Species, SpeciesEntry> TO_SPECIES_ENTRY = new AbstractEntryConverter<Species, SpeciesEntry>() {

		@Override
		public SpeciesEntry convert(Species species) {
			return new SpeciesEntry(species.getAlphaCode(), species.getAlphaCode(), species.getNameEn());
		}
	};
	
	public static final EntryConverter<Measure, MeasureEntry> TO_MEASURE_ENTRY = new AbstractEntryConverter<Measure, MeasureEntry>() {

		@Override
		public MeasureEntry convert(Measure measure) {
			return new MeasureEntry(measure.getCode(), 
					measure.getSymbol(), 
					measure.getTitle(), 
					measure.getDocumentType()!=null?measure.getDocumentType().getDescription():null,
					measure.getMeasureYear(), 
					measure.getBinding(),
					measure.getInformationSources());
		}
	};
	
	public static final EntryConverter<CustomSpeciesGrp, GroupEntry> TO_GROUP_ENTRY = new AbstractEntryConverter<CustomSpeciesGrp, GroupEntry>() {

		@Override
		public GroupEntry convert(CustomSpeciesGrp group) {
			return new GroupEntry(group.getCode(), 
					group.getCustomSpeciesGrp());
		}
	};
	
	public static final EntryConverter<PoA, PoAEntry> TO_POA_ENTRY = new AbstractEntryConverter<PoA, PoAEntry>() {

		@Override
		public PoAEntry convert(PoA poa) {
			return new PoAEntry(poa.getCode(), 
					poa.getTitle(), 
					poa.getPoAYear(), 
					poa.getPoAType(),
					poa.getStatus());
		}
	};
	
	public static final EntryConverter<MgmtEntity, EntityEntry> TO_ENTITY_ENTRY = new AbstractEntryConverter<MgmtEntity, EntityEntry>() {

		@Override
		public EntityEntry convert(MgmtEntity entity) {
			return new EntityEntry(entity.getCode(), 
					entity.getAcronym(), 
					entity.getMgmtEntityName());
		}

	};
	
	private static abstract class AbstractEntryConverter<I,O> implements EntryConverter<I,O> {

		@Override
		public O apply(I t) {
			return convert(t);
		}
	}
	
	public interface EntryConverter<I,O> extends Function<I, O>{
		public O convert(I item);
	}
}
