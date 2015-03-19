
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refSpecies")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class Species {

    @Id
    @Column(name = "cdSpecies")
    private Long code;
    @Column(name = "3AlphaCode")
    private String alphaCode;
    @Column
    private String scientificName;
    @Column
    private String nameEn;
    @Column
    private String nameFr;
    @Column
    private String nameSp;
    @Column
    private String urlFactSheet;

}
