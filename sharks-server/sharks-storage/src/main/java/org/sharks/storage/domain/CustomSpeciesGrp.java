
package org.sharks.storage.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refCustomSpeciesGrp")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class CustomSpeciesGrp {

    @Id
    @Column(name = "cdCustomSpeciesGrp")
    private Long code;
    @Column
    private String customSpeciesGrp;
    @Column
    private String description;
    @OneToMany
    @JoinTable(name = "grpCustSpeciesSpecies", joinColumns = @JoinColumn(name = "cdCustomSpeciesGrp", referencedColumnName = "cdCustomSpeciesGrp"), inverseJoinColumns = @JoinColumn(name = "cdSpecies", referencedColumnName = "cdSpecies"))
    private List<Species> species;

}
