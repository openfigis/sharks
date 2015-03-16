
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refMeasureTag")
@XmlRootElement
public class MeasureTag {

    @Id
    @Column(name = "cdMeasureTag")
    private Long code;
    @Column
    private String description;

}
