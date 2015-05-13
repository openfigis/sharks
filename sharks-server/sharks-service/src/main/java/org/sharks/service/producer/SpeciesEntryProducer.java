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
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
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
		
		RefPubSpecies refPubSpecies = refpub.getSpecies(species.getAlphaCode());
		if (refPubSpecies!=null) {
			scientificName = refPubSpecies.getScientificName();
			MultiLingualName longNames = refPubSpecies.getLongNames();
			if (longNames!=null) englishName = longNames.getEnglish();
		}
		
		return new SpeciesEntry(species.getAlphaCode(), scientificName, englishName);
	}
}
