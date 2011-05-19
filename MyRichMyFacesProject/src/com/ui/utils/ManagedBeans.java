/**
 * 
 */
package com.ui.utils;

import com.ui.beans.common.MBError;
import com.ui.beans.common.MBProgressBar;

/**
 * Списък на MB
 * 
 * @author developer0024
 * 
 */
public enum ManagedBeans {

	/**
	 * Грешка
	 */
	error(MBError.class),

	/**
	 * Прогрес
	 */
	progressBar(MBProgressBar.class);

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
