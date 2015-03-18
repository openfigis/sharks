/**
 * 
 */
package org.sharks.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.sharks.storage.domain.InformationSource;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@XmlRootElement
@Data
public class MeasureDetails {

	private long id;
	private String symbol;
	private String title;
	private int year;
	private List<InformationSource> sources;
	
}
