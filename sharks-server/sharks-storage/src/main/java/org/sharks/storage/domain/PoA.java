
package org.sharks.storage.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "tbPoA")
@XmlRootElement
public class PoA {

    @Id
    @Column(name = "cdPoA")
    private Long code;
    @Column
    private String description;
    @Column
    private Integer poAYear;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpPoACountry", joinColumns = @JoinColumn(name = "cdPoA", referencedColumnName = "cdPoA"), inverseJoinColumns = @JoinColumn(name = "cdCountry", referencedColumnName = "cdCountry"))
    private List<Country> countries;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpPoAInformationSource", joinColumns = @JoinColumn(name = "cdPoA", referencedColumnName = "cdPoA"), inverseJoinColumns = @JoinColumn(name = "cdInformationSource", referencedColumnName = "cdInformationSource"))
    private List<InformationSource> informationSources;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpPoAMgmtEntity", joinColumns = @JoinColumn(name = "cdPoA", referencedColumnName = "cdPoA"), inverseJoinColumns = @JoinColumn(name = "cdMgmtEntity", referencedColumnName = "cdMgmtEntity"))
    private List<MgmtEntity> mgmtEntities;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdPoAType")
    private PoAType poAType;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdStatus")
    private Status status;

}
