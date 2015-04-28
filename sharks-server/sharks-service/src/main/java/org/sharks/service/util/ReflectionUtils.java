/**
 * 
 */
package org.sharks.service.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ReflectionUtils {

	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

	private static Set<Class<?>> getWrapperTypes()
	{
		Set<Class<?>> wrappers = new HashSet<Class<?>>();
		wrappers.add(Boolean.class);
		wrappers.add(Character.class);
		wrappers.add(Byte.class);
		wrappers.add(Short.class);
		wrappers.add(Integer.class);
		wrappers.add(Long.class);
		wrappers.add(Float.class);
		wrappers.add(Double.class);
		wrappers.add(Void.class);
		return wrappers;
	}

	public static boolean isWrapperType(Class<?> clazz)
	{
		return WRAPPER_TYPES.contains(clazz);
	}

	public static List<PropertyDescriptor> getPropertyDescriptors(Class<?> type) {

		try {
			BeanInfo info = Introspector.getBeanInfo(type);

			List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
			for (PropertyDescriptor descriptor:info.getPropertyDescriptors()) {
				Class<?> propertyType = descriptor.getPropertyType();
				if (isWrapperType(propertyType) 
						|| propertyType.isPrimitive()
						|| propertyType == String.class) {
					descriptors.add(descriptor);
				}
			}
			return descriptors;

		} catch (IntrospectionException e) {
			throw new RuntimeException("Failed analyzing type "+type, e);
		}
	}
	
	public static <T> Object getPropertyValue(PropertyDescriptor descriptor, T item) {
		try {
			return descriptor.getReadMethod().invoke(item);
		} catch (Exception e) {
			throw new RuntimeException("Failed getting property value "+descriptor.getName(), e);
		}
	}
	
	private ReflectionUtils(){}


}
