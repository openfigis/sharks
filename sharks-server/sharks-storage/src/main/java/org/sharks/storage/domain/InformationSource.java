
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
@Entity(name = "tbInformationSource")
@XmlRootElement
public class InformationSource {

    @Id
    @Column(name = "cdInformationSource")
    private Long code;
    @Column
    private String url;
    @Column
    private String citation;
    @Column
    private Integer informationSourceYear;

}
