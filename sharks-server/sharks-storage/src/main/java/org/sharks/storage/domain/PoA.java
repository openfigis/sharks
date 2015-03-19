
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
@Entity(name = "tbPoA")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class PoA {

    @Id
    @Column(name = "cdPoA")
    private Long code;
    @Column
    private String description;
    @Column
    private Integer poAYear;
    @OneToMany
    @JoinTable(name = "grpPoACountry", joinColumns = @JoinColumn(name = "cdPoA", referencedColumnName = "cdPoA"), inverseJoinColumns = @JoinColumn(name = "cdCountry", referencedColumnName = "cdCountry"))
    private List<Country> countries;
    @OneToMany
    @JoinTable(name = "grpPoAInformationSource", joinColumns = @JoinColumn(name = "cdPoA", referencedColumnName = "cdPoA"), inverseJoinColumns = @JoinColumn(name = "cdInformationSource", referencedColumnName = "cdInformationSource"))
    private List<InformationSource> informationSources;
    @OneToMany
    @JoinTable(name = "grpPoAMgmtEntity", joinColumns = @JoinColumn(name = "cdPoA", referencedColumnName = "cdPoA"), inverseJoinColumns = @JoinColumn(name = "cdMgmtEntity", referencedColumnName = "cdMgmtEntity"))
    private List<MgmtEntity> mgmtEntities;
    @OneToOne
    @JoinColumn(name = "cdPoAType")
    private PoAType poAType;
    @OneToOne
    @JoinColumn(name = "cdStatus")
    private Status status;

}