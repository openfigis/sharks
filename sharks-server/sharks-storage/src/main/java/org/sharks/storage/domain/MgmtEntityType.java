
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
@Entity(name = "refMgmtEntityType")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class MgmtEntityType {

    @Id
    @Column(name = "cdMgmtEntityType")
    private Long code;
    @Column
    private String description;

}
