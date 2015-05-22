
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Derived from <code>refInformationSourceType</code> table .
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refInformationSourceType")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class InformationSourceType {

    @Id
    @Column(name = "cdInformationType")
    private Long code;
    @Column
    private String description;

}
