
package org.sharks.storage.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "tbMgmtEntity")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class MgmtEntity {

    @Id
    @Column(name = "cdMgmtEntity")
    private Long code;
    @Column
    private String acronym;
    @Column
    private String mgmtEntityName;
    @Column
    private String urlWebPage;
    @OneToMany
    @JoinTable(name = "grpMgmtEntityCountries", joinColumns = @JoinColumn(name = "cdMgmtEntity", referencedColumnName = "cdMgmtEntity"), inverseJoinColumns = @JoinColumn(name = "cdCountry", referencedColumnName = "cdCountry"))
    private List<Country> countries;
    @OneToOne
    @JoinColumn(name = "cdMgmtEntityType")
    private MgmtEntityType mgmtEntityType;

}
