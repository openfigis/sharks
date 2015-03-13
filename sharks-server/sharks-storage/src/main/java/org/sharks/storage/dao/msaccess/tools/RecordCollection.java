package org.sharks.storage.dao.msaccess.tools;

import java.util.List;

public class RecordCollection {

	private Class<?> clazz;
	private List<Object> objectList;

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public List<Object> getObjectList() {
		return objectList;
	}

	/**
	 * these objects can be records or domain objects
	 * 
	 * @param objects
	 */
	public void setObjectList(List<Object> objects) {
		this.objectList = objects;
	}

}
