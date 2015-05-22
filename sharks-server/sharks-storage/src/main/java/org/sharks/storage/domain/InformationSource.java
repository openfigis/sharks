
package org.sharks.storage.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * Derived from <code>tbInformationSource</code> table .
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "tbInformationSource")
@XmlRootElement
@EqualsAndHashCode(of = "code")
@ToString(exclude = {
    "mgmtEntities"
})
public class InformationSource {

    @Id
    @Column(name = "cdInformationSource")
    private Long code;
    @Column
    private String symbol;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String url;
    @Column
    private String citation;
    @Column
    private Integer infoSrcYear;
    @Column
    private String note;
    @Column
    private String publisher;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpInfoSourceMgmtEntity", joinColumns = @JoinColumn(name = "cdInformationSource", referencedColumnName = "cdInformationSource"), inverseJoinColumns = @JoinColumn(name = "cdMgmtEntity", referencedColumnName = "cdMgmtEntity"))
    private List<MgmtEntity> mgmtEntities;
    @OneToOne
    @JoinColumn(name = "cdInformationType")
    private InformationSourceType informationType;

}
