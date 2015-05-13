
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
    private String title;
    @Column
    private Integer poAYear;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpPoAInformationSource", joinColumns = @JoinColumn(name = "cdPoA", referencedColumnName = "cdPoA"), inverseJoinColumns = @JoinColumn(name = "cdInformationSource", referencedColumnName = "cdInformationSource"))
    private List<InformationSource> informationSources;
    @OneToOne
    @JoinColumn(name = "cdPoAType")
    private PoAType poAType;
    @OneToOne
    @JoinColumn(name = "cdStatus")
    private Status status;
    @OneToOne
    @JoinColumn(name = "cdMgmtEntity")
    private MgmtEntity mgmtEntity;

}
