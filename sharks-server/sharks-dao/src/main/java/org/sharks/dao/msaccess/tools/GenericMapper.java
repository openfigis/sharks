package org.sharks.dao.msaccess.tools;

import java.lang.reflect.Method;
import java.sql.ResultSet;

import org.sharks.dao.SharksException;

/**
 * Generate an object, given a resultset
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public class GenericMapper {

	/**
	 * 
	 * @param rs
	 *            coming from the Ms Acces DB.
	 * @param clazz
	 *            to map the resultset to.
	 * @return an instance of the class with
	 */
	public Object generateObject(ResultSet rs, Class<?> clazz) {

		try {
			Object object = clazz.newInstance();
			Method[] ms = clazz.getMethods();
			for (Method method : ms) {
				String attributeName = method.getName().substring(3, method.getName().length());
				if (method.getName().startsWith("set")) {
					clazz = method.getParameterTypes()[0];
					if (clazz == String.class) {
						method.invoke(object, rs.getString(attributeName));
					}
					if (clazz == int.class) {
						method.invoke(object, rs.getInt(attributeName));
					}
					if (clazz != String.class && clazz != int.class) {
						throw new SharksException("This type is not yet supported :" + clazz.getSimpleName());
					}
				}
			}
			return object;
		} catch (Exception e) {
			throw new SharksException(e);
		}
	}
}
