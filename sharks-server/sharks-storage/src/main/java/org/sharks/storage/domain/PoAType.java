
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Derived from <code>refPoAType</code> table .
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refPoAType")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class PoAType {

    @Id
    @Column(name = "cdPoAType")
    private String code;
    @Column
    private String description;

}
