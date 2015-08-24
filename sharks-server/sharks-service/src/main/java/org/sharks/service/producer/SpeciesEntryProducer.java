/**
 * 
 */
package org.sharks.service.producer;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.dto.SpeciesEntry;
import org.sharks.service.producer.EntryProducers.AbstractEntryProducer;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.MultiLingualName;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.storage.domain.Species;

/**
 * Merge information from {@link RefPubService} if these are missing from the source.
 * The field retrieved are scientific name and English name.
 * 
 * @author "Federico De Faveri federico.defaveri@fao.org"
 */
@Singleton
public class SpeciesEntryProducer extends AbstractEntryProducer<Species, SpeciesEntry> {
	
	@Inject
	private RefPubService refpub;

	@Override
	public SpeciesEntry produce(Species species) {
		if (species == null) return null;
		
		String scientificName = species.getScientificName();
		String englishName = species.getNameEn();
		String frenchName = null;
		String spanishName = null;
		String arabicName = null;
		String chineseName = null;
		String russianName = null;
		
		boolean hasMeasures = hasMeasures(species);
		
		RefPubSpecies refPubSpecies = refpub.getSpecies(species.getAlphaCode());
		if (refPubSpecies!=null) {
			scientificName = refPubSpecies.getScientificName();
			MultiLingualName longNames = refPubSpecies.getLongNames();
			if (longNames!=null) {
				englishName = longNames.getEnglish();
				frenchName = longNames.getFrench();
				spanishName = longNames.getSpanish();
				arabicName = longNames.getArabic();
				chineseName = longNames.getChinese();
				russianName = longNames.getRussian();
			}
		}
		
		return new SpeciesEntry(
				species.getAlphaCode(),
				scientificName, 
				englishName,
				frenchName,
				spanishName,
				arabicName,
				chineseName,
				russianName,
				hasMeasures
				);
	}
	
	private boolean hasMeasures(Species species) {
		return !species.getMeasures().isEmpty() 
				|| species.getCustomSpeciesGrps().stream().anyMatch(group->!group.getMeasures().isEmpty());
	}
}
