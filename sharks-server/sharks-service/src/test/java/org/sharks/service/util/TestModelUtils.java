/**
 * 
 */
package org.sharks.service.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.service.moniker.dto.FigisDoc.Member;
import org.sharks.service.refpub.dto.MultiLingualName;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.storage.domain.Country;
import org.sharks.storage.domain.CustomSpeciesGrp;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.PoA;
import org.sharks.storage.domain.PoAType;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestModelUtils {
	
	public static RefPubSpecies aRefPubSpecies() {
		return createRefPubSpecies("Alopias vulpinus", "2008", "Thresher", "Renard", "Zorro");
	}
	
	public static Species aSpecies() {
		return buildSpecies("ALV", "Alopias vulpinus", "Thresher");
	}
	
	public static RefPubCountry aRefPubCountry() {
		return createRefPubCountry("AFG", "Asia", "Afghanistan");
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
		species.setCustomSpeciesGrps(Collections.emptyList());
		species.setMeasures(Collections.emptyList());
		return species;
	}
	
	public static Country buildCountry(String iso3code, String unName, PoA ... poas) {
		Country country = new Country();
		country.setCode(iso3code);
		country.setUnName(unName);
		country.setPoAs(Arrays.asList(poas));
		return country;
	}
	
	public static MgmtEntity builEntity(long code, String acronym) {
		MgmtEntity entity = new MgmtEntity();
		entity.setCode(code);
		entity.setAcronym(acronym);
		return entity;
	}
	
	public static CustomSpeciesGrp buildCustomSpeciesGrp(long code, String name, Species ... species) {
		CustomSpeciesGrp group = new CustomSpeciesGrp();
		group.setCode(code);
		group.setCustomSpeciesGrp(name);
		group.setMeasures(Collections.emptyList());
		group.setSpecies(Arrays.asList(species));
		return group;
	}
	
	public static Measure buildMeasure(long code, String symbol) {
		Measure measure = new Measure();
		measure.setCode(code);
		measure.setSymbol(symbol);
		measure.setBinding(false);
		measure.setInformationSources(Collections.emptyList());
		return measure;
	}
	
	public static PoA buildPoA() {
		PoA poa = new PoA();
		poa.setInformationSources(Collections.emptyList());
		poa.setPoAType(new PoAType());		
		return poa;
	}
	
	public static RefPubCountry createRefPubCountry(String unIso3Code, String continent, String english) {
		return new RefPubCountry(unIso3Code, continent, Collections.emptyList(), new MultiLingualName(english, "french", "spanish", "arabic", "chinese", "russian"));
	}
	
	public static FigisDoc createFigisDoc(String acronym, String figisId, String imageId, String website, Member...members) {
		FigisDoc doc = new FigisDoc();
		doc.setAcronym(acronym);
		doc.setFigisId(figisId);
		doc.setImageId(imageId);
		doc.setWebsite(website);
		doc.setMembers(Arrays.asList(members));
		return doc;
	}
	
	public static Member createMember(String englishName, String iso3code) {
		Member member = new Member();
		member.setEnglishName(englishName);
		member.setIso3code(iso3code);
		return member;
	}
	
	public static <T> T findFirst(List<T> items, Predicate<T> predicate) {
		return items.stream().filter(predicate).findFirst().orElse(null);
	}

}
