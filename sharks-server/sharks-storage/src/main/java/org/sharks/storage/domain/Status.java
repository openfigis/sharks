
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Derived from <code>refStatus</code> table .
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refStatus")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class Status {

    @Id
    @Column(name = "cdStatus")
    private Long code;
    @Column
    private String description;

}
