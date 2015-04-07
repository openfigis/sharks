
package org.sharks.storage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "tbInformationSource")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class InformationSource {

    @Id
    @Column(name = "cdInformationSource")
    private Long code;
    @Column
    private String symbol;
    @Column
    private String title;
    @Column
    private String url;
    @Column
    private String citation;
    @Column
    private Integer infoSrcYear;
    @Column(name = "Abstract")
    private String abstractField;
    @Column
    private String note;
    @Column
    private String publisher;
    @OneToOne
    @JoinColumn(name = "cdInformationType")
    private InformationSourceType informationType;
    @OneToOne
    @JoinColumn(name = "cdMgmtEntity")
    private MgmtEntity mgmtEntity;

}
