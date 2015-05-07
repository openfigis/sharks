/**
 * 
 */
package org.sharks.service.util;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.sharks.service.refpub.dto.MultiLingualName;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class ComplementUtil {
	
	public Map<String, String> toNameMap(MultiLingualName name) {
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
