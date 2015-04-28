/**
 * 
 */
package org.sharks.service.dto;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement
public class CountryDetails {

	private final String code;
	private final String name;
	private final Map<String, String> officialNames;
	private final List<String> rfbs;
}
