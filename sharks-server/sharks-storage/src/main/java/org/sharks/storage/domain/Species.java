
package org.sharks.storage.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * Derived from <code>refSpecies</code> table .
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refSpecies")
@XmlRootElement
@EqualsAndHashCode(of = "code")
@ToString(exclude = {
    "customSpeciesGrps"
})
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
    private String addInfo;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpCustSpeciesSpecies", joinColumns = @JoinColumn(name = "cdSpecies", referencedColumnName = "cdSpecies"), inverseJoinColumns = @JoinColumn(name = "cdCustomSpeciesGrp", referencedColumnName = "cdCustomSpeciesGrp"))
    private List<CustomSpeciesGrp> customSpeciesGrps;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpMeasureSpecies", joinColumns = @JoinColumn(name = "cdSpecies", referencedColumnName = "cdSpecies"), inverseJoinColumns = @JoinColumn(name = "cdMeasure", referencedColumnName = "cdMeasure"))
    private List<Measure> measures;

}
