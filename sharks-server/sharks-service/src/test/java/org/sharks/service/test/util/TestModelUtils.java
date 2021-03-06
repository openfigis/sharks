/**
 * 
 */
package org.sharks.service.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.sharks.service.geoserver.dto.SpeciesItem;
import org.sharks.service.geoserver.dto.SpeciesList;
import org.sharks.service.informea.dto.InformeaCountry;
import org.sharks.service.moniker.dto.Rfb;
import org.sharks.service.moniker.dto.Rfb.Member;
import org.sharks.service.refpub.dto.MultiLingualName;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.storage.domain.CustomSpeciesGrp;
import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.InformationSourceType;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.MgmtEntityType;
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

	public static RefPubSpecies createRefPubSpecies(String scientificName, String ficItem, String english,
			String french, String spanish) {
		return new RefPubSpecies(scientificName, ficItem,
				new MultiLingualName(english, french, spanish, "arabic", "chinese", "russian"));
	}

	public static Species buildSpecies(String alphaCode, String... names) {
		Species species = new Species();
		species.setAlphaCode(alphaCode);
		if (names.length > 0)
			species.setScientificName(names[0]);
		if (names.length > 1)
			species.setNameEn(names[1]);
		species.setCustomSpeciesGrps(Collections.emptyList());
		species.setMeasures(Collections.emptyList());
		return species;
	}

	public static MgmtEntity buildCountry(String iso3code, String unName, PoA... poas) {
		MgmtEntity country = new MgmtEntity();
		country.setAcronym(iso3code);
		country.setMgmtEntityName(unName);
		country.setPoAs(Arrays.asList(poas));
		country.setInformationSources(Collections.emptyList());
		return country;
	}

	public static MgmtEntity buildEntity(long code, String acronym, InformationSource... sources) {
		MgmtEntity entity = new MgmtEntity();
		entity.setCode(code);
		entity.setAcronym(acronym);
		entity.setInformationSources(Arrays.asList(sources));
		entity.setMeasures(Collections.emptyList());
		entity.setMgmtEntityType(new MgmtEntityType());
		return entity;
	}

	public static CustomSpeciesGrp buildCustomSpeciesGrp(long code, String name, Species... species) {
		CustomSpeciesGrp group = new CustomSpeciesGrp();
		group.setCode(code);
		group.setCustomSpeciesGrp(name);
		group.setMeasures(Collections.emptyList());
		group.setSpecies(Arrays.asList(species));
		return group;
	}

	public static InformationSource buildInformationSource(long type) {
		InformationSource source = new InformationSource();
		InformationSourceType informationType = new InformationSourceType();
		informationType.setCode(type);
		source.setInformationType(informationType);
		return source;
	}

	// public static List<InformationSource> buildInformationSources(long type) {
	// List<InformationSource> l = new ArrayList<InformationSource>();
	// l.add(buildInformationSource(type));
	// return l;
	// }

	public static Measure buildMeasure(long code, String symbol) {
		Measure measure = new Measure();
		measure.setCode(code);
		measure.setSymbol(symbol);
		measure.setBinding(false);
		measure.setHide(false);
		measure.setInformationSources(Collections.emptyList());
		measure.setMgmtEntity(buildEntity(code, "", buildInformationSource(code)));
		return measure;
	}

	public static PoA buildPoA() {
		PoA poa = new PoA();
		poa.setInformationSources(Collections.emptyList());
		poa.setPoAType(new PoAType());
		return poa;
	}

	public static RefPubCountry createRefPubCountry(String unIso3Code, String continent, String english) {
		return new RefPubCountry(unIso3Code, "", continent, Collections.emptyList(),
				new MultiLingualName(english, "french", "spanish", "arabic", "chinese", "russian"));
	}

	public static Member createMember(String englishName, String iso3code) {
		Member member = new Member();
		member.setEnglishName(englishName);
		member.setIso3(iso3code);
		return member;
	}

	public static Rfb createRfb(String fid, String acronym, Member... members) {
		Rfb rfb = new Rfb();
		rfb.setFid(fid);
		rfb.setAcronym(acronym);
		rfb.setMembers(Arrays.asList(members));
		return rfb;
	}

	public static Rfb createRfbEntry(String figisId) {
		Rfb rfb = new Rfb();
		rfb.setFigisId(figisId);
		return rfb;
	}

	public static SpeciesList createSpeciesList(String... alpha3codes) {
		SpeciesList list = new SpeciesList();
		List<SpeciesItem> items = new ArrayList<SpeciesItem>();
		for (String alpha3code : alpha3codes) {
			SpeciesItem item = new SpeciesItem();
			item.setAlphaCode(alpha3code);
			items.add(item);
		}
		list.setItems(items);

		return list;
	}

	public static InformeaCountry createCitesCountry(String iso3, String iso2, String name) {
		InformeaCountry country = new InformeaCountry();
		country.setIso2(iso2);
		country.setIso3(iso3);
		country.setName(name);
		return country;
	}

	public static <T> T findFirst(List<T> items, Predicate<T> predicate) {
		return items.stream().filter(predicate).findFirst().orElse(null);
	}

}
