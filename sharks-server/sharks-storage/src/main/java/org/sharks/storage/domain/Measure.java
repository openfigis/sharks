
package org.sharks.storage.domain;

import java.util.Date;
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


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "tbMeasure")
@XmlRootElement
public class Measure {

    @Id
    @Column(name = "cdMeasure")
    private Long code;
    @Column
    private String symbol;
    @Column(name = "fgBinding")
    private Boolean binding;
    @Column
    private Long cdMeasureReplaces;
    @Column
    private String reservation;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String note;
    @Column(name = "dtAdoption")
    private Date adoption;
    @Column(name = "dtStart")
    private Date start;
    @Column(name = "dtEnd")
    private Date end;
    @Column
    private Integer measureYear;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpMeasureInformationSource", joinColumns = @JoinColumn(name = "cdMeasure", referencedColumnName = "cdMeasure"), inverseJoinColumns = @JoinColumn(name = "cdInformationSource", referencedColumnName = "cdInformationSource"))
    private List<InformationSource> informationSources;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpMeasureSpecies", joinColumns = @JoinColumn(name = "cdMeasure", referencedColumnName = "cdMeasure"), inverseJoinColumns = @JoinColumn(name = "cdSpecies", referencedColumnName = "cdSpecies"))
    private List<Species> species;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpMeasureTags", joinColumns = @JoinColumn(name = "cdMeasure", referencedColumnName = "cdMeasure"), inverseJoinColumns = @JoinColumn(name = "cdMeasureTag", referencedColumnName = "cdMeasureTag"))
    private List<MeasureTag> measureTags;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdMgmtEntity")
    private MgmtEntity mgmtEntity;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdDocumentType")
    private DocumentType documentType;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdArea")
    private Area area;

}
