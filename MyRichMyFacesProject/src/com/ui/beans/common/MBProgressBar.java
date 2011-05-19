package com.ui.beans.common;

public class MBProgressBar {

	private Long curentProgress;

	public MBProgressBar() {
		super();
		this.curentProgress = new Long(0);
	}

	/**
	 * 
	 * @return Long
	 */
	public Long getCurentProgress() {
		return curentProgress;
	}

	public void setCurentProgress(Long curentProgress) {
		this.curentProgress = curentProgress;
	}

}
