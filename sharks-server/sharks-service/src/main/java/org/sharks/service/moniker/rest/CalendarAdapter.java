package org.sharks.service.moniker.rest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CalendarAdapter extends XmlAdapter<String, Calendar> {

    private SimpleDateFormat[] dateFormats = {
    		new SimpleDateFormat("yyyy"),
    		new SimpleDateFormat("yyyy-MM"),
    		new SimpleDateFormat("yyyy-MM-dd")
    	};

    @Override
    public String marshal(Calendar v) throws Exception {
        return dateFormats[2].format(v.getTime());
    }

    @Override
    public Calendar unmarshal(String v) throws Exception {
    	if (v == null || v.isEmpty()) return null;
    	int numDash = countDashes(v);
    	SimpleDateFormat dateFormat = getFormat(numDash);
		Calendar cal = Calendar.getInstance();
	    cal.setTime(dateFormat.parse(v));
        return cal;
    }
    
    private SimpleDateFormat getFormat(int numDash) {
    	int index = Math.min(numDash, dateFormats.length);
    	return dateFormats[index];
    }
    
    private int countDashes(String v) {
    	if (v == null) return 0;
    	int count = 0;
    	for (char c:v.toCharArray()) if (c == '-') count++;
    	return count;
    }

}