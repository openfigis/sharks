
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Derived from <code>refGeoCoverageType</code> table .
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refGeoCoverageType")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class GeoCoverageType {

    @Id
    @Column(name = "cdGeoCoverage")
    private Long code;
    @Column
    private String description;

}
