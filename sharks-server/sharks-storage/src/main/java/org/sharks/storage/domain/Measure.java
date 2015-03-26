
package org.sharks.storage.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "tbMeasure")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class Measure {

    @Id
    @Column(name = "cdMeasure")
    private Long code;
    @Column
    private String symbol;
    @Column
    private String title;
    @Column(name = "fgBinding")
    private Boolean binding;
    @Column
    private String reservation;
    @Column
    private String description;
    @Column
    private String note;
    @Temporal(TemporalType.DATE)
    @Column(name = "dtAdoption")
    private Date adoption;
    @Temporal(TemporalType.DATE)
    @Column(name = "dtStart")
    private Date start;
    @Temporal(TemporalType.DATE)
    @Column(name = "dtEnd")
    private Date end;
    @Column
    private Integer measureYear;
    @Column
    private Long cdMeasureReplaces;
    @OneToMany
    @JoinTable(name = "grpMeasureCustSpecies", joinColumns = @JoinColumn(name = "cdMeasure", referencedColumnName = "cdMeasure"), inverseJoinColumns = @JoinColumn(name = "cdCustomSpeciesGrp", referencedColumnName = "cdCustomSpeciesGrp"))
    private List<CustomSpeciesGrp> customSpeciesGrps;
    @OneToMany
    @JoinTable(name = "grpMeasureInformationSource", joinColumns = @JoinColumn(name = "cdMeasure", referencedColumnName = "cdMeasure"), inverseJoinColumns = @JoinColumn(name = "cdInformationSource", referencedColumnName = "cdInformationSource"))
    private List<InformationSource> informationSources;
    @OneToMany
    @JoinTable(name = "grpMeasureSpecies", joinColumns = @JoinColumn(name = "cdMeasure", referencedColumnName = "cdMeasure"), inverseJoinColumns = @JoinColumn(name = "cdSpecies", referencedColumnName = "cdSpecies"))
    private List<Species> species;
    @OneToMany
    @JoinTable(name = "grpMeasureTags", joinColumns = @JoinColumn(name = "cdMeasure", referencedColumnName = "cdMeasure"), inverseJoinColumns = @JoinColumn(name = "cdMeasureTag", referencedColumnName = "cdMeasureTag"))
    private List<MeasureTag> measureTags;
    @OneToOne
    @JoinColumn(name = "cdMgmtEntity")
    private MgmtEntity mgmtEntity;
    @OneToOne
    @JoinColumn(name = "cdDocumentType")
    private DocumentType documentType;
    @OneToOne
    @JoinColumn(name = "cdArea")
    private Area area;

}
