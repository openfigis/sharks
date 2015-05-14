/**
 * 
 */
package org.sharks.service.dto;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoAEntry {

    private Long code;
    private String title;
    private Integer poAYear;
    private String type;
    private String status;
	
}
