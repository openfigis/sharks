
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Derived from <code>tbConfigText</code> table .
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "tbConfigText")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class ConfigText {

    @Id
    @Column(name = "cdConfigText")
    private Long code;
    @Column
    private String cdMnemonicKey;
    @Column
    private String description;

}
