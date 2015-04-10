/**
 * 
 */
package org.sharks.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.sharks.storage.domain.InformationSource;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasureEntry {

	private long id;
	private String symbol;
	private String title;
	private Integer year;
	private List<InformationSource> sources;
	
}
