package com.ui.beans.common;

import com.ui.helpers.ManagedBeanHelper;
import com.ui.utils.ManagedBeans;

public class MBUser {

	public void addProgress() {
		ManagedBeanHelper helper = new ManagedBeanHelper();
		MBProgressBar mbProgress = (MBProgressBar) helper.findBeanInCtx(ManagedBeans.progressBar);
		mbProgress.setCurentProgress(mbProgress.getCurentProgress() + 10);
	}
}
