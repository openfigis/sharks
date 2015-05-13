
package org.sharks.storage.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 * 
 */
@Data
@Entity(name = "refCountry")
@XmlRootElement
@EqualsAndHashCode(of = "code")
public class Country {

    @Id
    @Column(name = "cdCountry")
    private String code;
    @Column
    private String unName;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grpPoACountry", joinColumns = @JoinColumn(name = "cdCountry", referencedColumnName = "cdCountry"), inverseJoinColumns = @JoinColumn(name = "cdPoA", referencedColumnName = "cdPoA"))
    private List<PoA> poAs;

}
