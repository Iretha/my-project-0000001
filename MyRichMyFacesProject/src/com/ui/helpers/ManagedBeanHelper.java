package com.ui.helpers;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import com.ui.utils.ManagedBeans;

public class ManagedBeanHelper {
	/**
	 * Намира текущата инстанция на ManagedBean-a от контекста и го връща
	 * 
	 * @param bean
	 * @return наследник на MBParent
	 */
	public Object findBeanInCtx(ManagedBeans bean) {
		FacesContext fctx = FacesContext.getCurrentInstance();
		ValueExpression vExpr = getMBValExp(fctx, bean);
		Object currBean = vExpr.getValue(fctx.getELContext());
		return currBean;
	}

	/**
	 * Генерира ValueExpression за извличане на MB от контекста
	 * 
	 * @param fctx
	 *            FacesContext
	 * @param bean
	 *            ManagedBeans
	 * @return ValueExpression
	 */
	private ValueExpression getMBValExp(FacesContext fctx, ManagedBeans bean) {
		ELContext elctx = fctx.getELContext();
		Application jsfApp = fctx.getApplication();
		ExpressionFactory exprFactory = jsfApp.getExpressionFactory();
		ValueExpression vExpr = exprFactory.createValueExpression(elctx, "#{" + bean.getPropName()
				+ "}", bean.getPropClazz());
		return vExpr;
	}
}
