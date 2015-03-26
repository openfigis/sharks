
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

}
