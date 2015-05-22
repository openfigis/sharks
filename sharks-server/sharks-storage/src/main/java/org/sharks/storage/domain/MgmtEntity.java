
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
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * Derived from <code>tbMgmtEntity</code> table .
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "tbMgmtEntity")
@XmlRootElement
@EqualsAndHashCode(of = "code")
@ToString(exclude = {
    "informationSources"
})
public class MgmtEntity {

    @Id
    @Column(name = "cdMgmtEntity")
    private Long code;
    @Column
    private String acronym;
    @Column
    private String mgmtEntityName;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpInfoSourceMgmtEntity", joinColumns = @JoinColumn(name = "cdMgmtEntity", referencedColumnName = "cdMgmtEntity"), inverseJoinColumns = @JoinColumn(name = "cdInformationSource", referencedColumnName = "cdInformationSource"))
    private List<InformationSource> informationSources;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpPoAMgmtEntity", joinColumns = @JoinColumn(name = "cdMgmtEntity", referencedColumnName = "cdMgmtEntity"), inverseJoinColumns = @JoinColumn(name = "cdPoA", referencedColumnName = "cdPoA"))
    private List<PoA> poAs;
    @OneToOne
    @JoinColumn(name = "cdMgmtEntityType")
    private MgmtEntityType mgmtEntityType;
    @OneToOne
    @JoinColumn(name = "cdGeoCoverage")
    private GeoCoverageType geoCoverage;

}
