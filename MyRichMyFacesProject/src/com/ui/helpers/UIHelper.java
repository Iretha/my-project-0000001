/**
 * 
 */
package com.ui.helpers;

import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.event.ActionListener;
import org.ajax4jsf.component.html.HtmlActionParameter;
import org.ajax4jsf.component.html.HtmlAjaxCommandLink;

import com.ui.utils.ManagedBeans;

/**
 * @author МBD
 */
public class UIHelper extends UIBaseHelper {

	public static String ID_PREFIX_PANEL = "pnl_";

	public static String ID_PREFIX_LINK = "lnk_";

	public static String ID_PREFIX_PARAM = "prm_";

	public HtmlPanelGrid genHPanelGrid(String id) {
		HtmlPanelGrid comp = new HtmlPanelGrid();
		setId(comp, ID_PREFIX_PANEL + id);
		return comp;
	}

	/**
	 * Генерира компонент
	 * 
	 * @param label
	 * @param id
	 * @param action
	 * @param listener
	 * @param ajaxSingle
	 * @param rendered
	 * @param reRender
	 * @return UIAjaxCommandLink
	 */
	public HtmlAjaxCommandLink getA4JCommandLInk(String id, String label, String title,
			UIComponent parent, MethodExpression action, ActionListener listener,
			Boolean ajaxSingle, Boolean rendered, Object reRender) {
		HtmlAjaxCommandLink comp = (HtmlAjaxCommandLink) createComponent(HtmlAjaxCommandLink.COMPONENT_TYPE);
		setId(comp, ID_PREFIX_LINK + id);
		setValue(comp, label);
		setTitle(comp, title);
		setParent(comp, parent);
		addActionListener(comp, listener);
		addAction(comp, action);
		setRendered(comp, rendered);
		setAjaxSingle(comp, ajaxSingle);
		setReRender(comp, reRender);
		return comp;
	}

	public MethodExpression getMethodExpression(String method, ManagedBeans mb) {
		return generateMethodExpression(generateExpressionString(method, mb));
	}

	public HtmlActionParameter getUIActionParameter(String id, String name, String value,
			String assignToExpr, ManagedBeans mb, UIComponent parent) {
		HtmlActionParameter comp = new HtmlActionParameter();
		setId(comp, ID_PREFIX_PARAM + id);
		setParent(comp, parent);
		comp.setName(name);
		comp.setValue(value);
		comp.setAssignToBinding(generateValueExpression(generateExpressionString(assignToExpr, mb)));
		return comp;
	}
}
