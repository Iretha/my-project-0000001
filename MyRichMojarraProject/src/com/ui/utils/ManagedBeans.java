/**
 * 
 */
package com.ui.utils;

import com.ui.beans.MBError;

/**
 * Списък на MB в ЕСУРПД
 * 
 * @author developer0024
 * 
 */
public enum ManagedBeans  {

	/**
	 * 
	 */
	error(MBError.class), ;

	private Class<? extends Object> cls;

	private ManagedBeans(Class<? extends Object> cls) {
		this.cls = cls;
	}

	/**
	 * @see IManagedProps#getPropClazz()
	 */
	public Class<? extends Object> getPropClazz() {
		return this.cls;
	}

	/**
	 * @see IManagedProps#getPropName()
	 */
	public String getPropName() {
		return name();
	}

}
