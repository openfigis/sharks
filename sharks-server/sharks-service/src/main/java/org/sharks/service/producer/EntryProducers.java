/**
 * 
 */
package org.sharks.service.producer;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.sharks.service.dto.EntityDocument;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.dto.FaoLexDocument;
import org.sharks.service.dto.GroupEntry;
import org.sharks.service.dto.InformationSourceEntry;
import org.sharks.service.dto.MeasureEntry;
import org.sharks.service.dto.PoAEntry;
import org.sharks.service.moniker.dto.FaoLexFiDocument;
import org.sharks.storage.domain.CustomSpeciesGrp;
import org.sharks.storage.domain.InformationSource;
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
			
			String replacedMeasureSourceUrl = null;
			
			if (measure.getReplaces()!=null) {
				Measure replaced = measure.getReplaces();
				List<InformationSource> replacedSources = replaced.getInformationSources();
				replacedMeasureSourceUrl = !replacedSources.isEmpty()?replacedSources.get(0).getUrl():null;
			}
			
			return new MeasureEntry(measure.getCode(), 
					measure.getSymbol(), 
					measure.getTitle(), 
					measure.getDocumentType()!=null?measure.getDocumentType().getDescription():null,
					measure.getMeasureYear(), 
					measure.getBinding(),
					findEntityAcronym(measure.getInformationSources()),
					convert(measure.getInformationSources(),TO_INFORMATION_SOURCE_ENTRY),
					replacedMeasureSourceUrl);
		}
		
		private String findEntityAcronym(List<InformationSource> sources) {
			for (InformationSource source:sources) {
				for (MgmtEntity mgmtEntity : source.getMgmtEntities()) {
					if (mgmtEntity!=null && mgmtEntity.getAcronym()!=null) return mgmtEntity.getAcronym();
				}
			}
			return null;
		}
	};
	
	public static final EntryProducer<InformationSource, InformationSourceEntry> TO_INFORMATION_SOURCE_ENTRY = new AbstractEntryProducer<InformationSource, InformationSourceEntry>() {

		@Override
		public InformationSourceEntry produce(InformationSource source) {
			return new InformationSourceEntry(source.getUrl());
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
					poa.getPoAType()!=null?poa.getPoAType().getDescription():null,
					poa.getStatus()!=null?poa.getStatus().getDescription():null,
					convert(poa.getInformationSources(), TO_INFORMATION_SOURCE_ENTRY));
		}
	};
	
	public static final EntryProducer<MgmtEntity, EntityEntry> TO_ENTITY_ENTRY = new AbstractEntryProducer<MgmtEntity, EntityEntry>() {

		@Override
		public EntityEntry produce(MgmtEntity entity) {
			return new EntityEntry(entity.getAcronym(), entity.getMgmtEntityName(), entity.getMgmtEntityType().getCode());
		}

	};
	
	public static final EntryProducer<InformationSource, EntityDocument> TO_ENTITY_DOCUMENT = new AbstractEntryProducer<InformationSource, EntityDocument>() {

		@Override
		public EntityDocument produce(InformationSource source) {
			return new EntityDocument(
					source.getTitle(), 
					source.getInfoSrcYear(), 
					source.getInformationType().getDescription(), 
					source.getUrl());
		}
	};
	
	public static final EntryProducer<FaoLexFiDocument, FaoLexDocument> TO_FAOLEX_DOCUMENT = new AbstractEntryProducer<FaoLexFiDocument, FaoLexDocument>() {

		@Override
		public FaoLexDocument produce(FaoLexFiDocument doc) {
			return new FaoLexDocument(
					doc.getFaolexId(),
					doc.getTitle(), 
					doc.getLongTitle(), 
					doc.getDateOfText(), 
					doc.getDateOfOriginalText(),
					doc.getDateOfConsolidation(),
					doc.getUri());
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
