/**
 * 
 */
package org.sharks.service.util;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.sharks.service.refpub.dto.MultiLingualName;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestUtils {
	
	public static RefPubSpecies aRefPubSpecies() {
		return createRefPubSpecies("Alopias vulpinus", "2008", "Thresher", "Renard", "Zorro");
	}
	
	public static Species aSpecies() {
		return buildSpecies("ALV", "Alopias vulpinus", "Thresher");
	}
	
	public static RefPubSpecies createRefPubSpecies(String scientificName, String ficItem, String english, String french, String spanish) {
		return new RefPubSpecies(scientificName, ficItem, new MultiLingualName(english, french, spanish, "arabic", "chinese", "russian"));
	}
	
	public static Species buildSpecies(String alphaCode, String ... names) {
		Species species = new Species();
		species.setAlphaCode(alphaCode);
		if (names.length>0) species.setScientificName(names[0]);
		if (names.length>1) species.setNameEn(names[1]);
		if (names.length>2) species.setNameFr(names[2]);
		if (names.length>3) species.setNameSp(names[3]);
		species.setMeasures(Collections.emptyList());
		return species;
	}
	
	public static <T> T findFirst(List<T> items, Predicate<T> predicate) {
		return items.stream().filter(predicate).findFirst().orElse(null);
	}

}
