/**
 * 
 */
package org.sharks.service.indexing;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

import org.sharks.service.indexing.IndexingService.FieldProvider;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class FieldProviders {
	
	public static <T> FieldProvider<T> byReflection(Class<T> type) {
		try {
			BeanInfo info = Introspector.getBeanInfo(type);
			
			Map<String, PropertyDescriptor> descriptors = new HashMap<String, PropertyDescriptor>();
			
			for (PropertyDescriptor descriptor:info.getPropertyDescriptors()) {
				Class<?> propertyType = descriptor.getPropertyType();
				if (isWrapperType(propertyType) 
						|| propertyType.isPrimitive()
						|| propertyType == String.class) {
					descriptors.put(descriptor.getName(), descriptor);
					
					if (descriptor.getName().equals("code")) descriptors.put("id", descriptor);
				}
			}
			return new ReflectionFieldProvider<T>(descriptors, type.getSimpleName());
		} catch (IntrospectionException e) {
			throw new RuntimeException("Failed analizying type "+type, e);
		}
	}
	
	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    public static boolean isWrapperType(Class<?> clazz)
    {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes()
    {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }
	
	@Data
	public static class ReflectionFieldProvider<T> implements FieldProvider<T> {

		private final Map<String, PropertyDescriptor> descriptors;
		private final String typeName;
		
		@Override
		public List<String> getFieldsName() {
			return new ArrayList<String>(descriptors.keySet());
		}

		@Override
		public String getFieldValue(T item, String name) {
			PropertyDescriptor descriptor = descriptors.get(name);
			if (descriptor == null) throw new IllegalArgumentException("Invalid field name "+name);
			try {
				return String.valueOf(descriptor.getReadMethod().invoke(item));
			} catch (Exception e) {
				throw new RuntimeException("Failed reading field property "+name, e);
			}
		}

		@Override
		public String getTypeName() {
			return typeName;
		}
		
	}

}
