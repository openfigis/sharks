
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Derived from <code>refDocumentType</code> table .
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refDocumentType")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class DocumentType {

    @Id
    @Column(name = "cdDocumentType")
    private Long code;
    @Column
    private String description;

}
