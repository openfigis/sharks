/**
 * 
 */
package org.sharks.service.dto;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.sharks.storage.domain.PoAType;
import org.sharks.storage.domain.Status;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoADetails {

    private Long code;
    private String title;
    private Integer poAYear;
    private PoAType poAType;
    private Status status;
	
}
