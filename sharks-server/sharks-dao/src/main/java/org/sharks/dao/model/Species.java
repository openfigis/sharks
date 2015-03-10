/**
 * 
 */
package org.sharks.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@NoArgsConstructor
@Entity(name="refSpecies")
public class Species {
	
	@Id
	@Column(name="cdSpecies")
	String code;
	
	@Column(name="3AlphaCode")
	String alphaCode;
	
	String scientificName;
	String nameEn;
	String nameFr;
	String nameSp;
	String urlFactSheet;
	
}
