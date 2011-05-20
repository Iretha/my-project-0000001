package com.ui.helpers;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;

import org.ajax4jsf.component.AjaxActionComponent;
import org.ajax4jsf.component.html.HtmlAjaxCommandLink;

import com.ui.beans.common.MBLocale;
import com.ui.utils.ManagedBeans;

public class UIBaseHelper {

	private Application app;

	public UIBaseHelper() {
		super();
		this.app = FacesContext.getCurrentInstance().getApplication();
	}

	protected UIComponent createComponent(String componentType) {
		return this.app.createComponent(componentType);
	}

	protected void setId(UIComponentBase comp, String id) {
		if (id != null) {
			comp.setId(id);
		}
	}

	protected void setParent(UIComponent comp, UIComponent parent) {
		if (parent != null) {
			comp.setParent(parent);
		}
	}

	protected void setValue(UICommand comp, String value) {
		if (value != null) {
			comp.setValue(value);
		}
	}

	protected void setTitle(HtmlAjaxCommandLink comp, String title) {
		if (title != null) {
			comp.setTitle(title);
		}
	}

	protected void addActionListener(UICommand comp, ActionListener listener) {
		if (listener != null) {
			comp.addActionListener(listener);
		}
	}

	protected void addAction(UICommand comp, MethodExpression action) {
		if (action != null) {
			comp.setActionExpression(action);
		}
	}

	protected void setRendered(UICommand comp, Boolean rendered) {
		if (rendered != null) {
			comp.setRendered(rendered.booleanValue());
		}
	}

	protected void setAjaxSingle(AjaxActionComponent comp, Boolean ajaxSingle) {
		if (ajaxSingle != null) {
			comp.setAjaxSingle(ajaxSingle.booleanValue());
		}
	}

	protected void setReRender(AjaxActionComponent comp, Object reRender) {
		if (reRender != null) {
			comp.setReRender(reRender);
		}
	}

	protected MethodExpression generateMethodExpression(String value) {
		MethodExpression actionExpression = null;
		if (value != null) {
			actionExpression = this.app.getExpressionFactory().createMethodExpression(
					FacesContext.getCurrentInstance().getELContext(), value, String.class,
					new Class[] {});
		}
		return actionExpression;
	}

	protected ValueExpression generateValueExpression(String value) {
		ValueExpression valueExpression = this.app.getExpressionFactory().createValueExpression(
				FacesContext.getCurrentInstance().getELContext(), value, MBLocale.class);
		return valueExpression;
	}

	public String generateExpressionString(String target, ManagedBeans mb) {
		String value = null;
		if (target == null) {
			return value;
		}
		if (mb != null) {
			value = "#" + "{" + mb.getPropName() + "." + target + "}";
		}
		return value;
	}

}
