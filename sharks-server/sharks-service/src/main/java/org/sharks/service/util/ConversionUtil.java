/**
 * 
 */
package org.sharks.service.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.sharks.service.refpub.dto.MultiLingualName;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ConversionUtil {
	
	public static Map<String, String> toNamesMap(MultiLingualName name) {
		if (name == null) return Collections.emptyMap();
		Map<String, String> names = new HashMap<String, String>();
		names.put("en", name.getEnglish());
		names.put("fr", name.getFrench());
		names.put("es", name.getSpanish());
		names.put("ar", name.getArabic());
		names.put("zh", name.getChinese());
		names.put("ru", name.getRussian());
		return names;
	}

}
