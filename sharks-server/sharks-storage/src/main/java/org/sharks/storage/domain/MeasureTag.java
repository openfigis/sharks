
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Derived from <code>refMeasureTag</code> table .
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refMeasureTag")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class MeasureTag {

    @Id
    @Column(name = "cdMeasureTag")
    private Long code;
    @Column
    private String description;

}
