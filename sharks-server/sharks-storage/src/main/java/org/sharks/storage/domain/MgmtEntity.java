
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @Column
    private String urlRfmoMapViewer;
    @OneToOne
    @JoinColumn(name = "cdMgmtEntityType")
    private MgmtEntityType mgmtEntityType;

}
