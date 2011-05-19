package com.ui.custom.components;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.webapp.UIComponentELTag;

/**
 * @author
 * 
 */
public class ParamTag extends UIComponentELTag {

	/**
	 * 
	 */
	static final String COMPONENT_FAMILY = "javax.faces.Parameter";

	private static final String NAME_ATTR = "name";

	private static final String VALUE_ATTR = "value";

	private String _name;

	private String _value;

	/**
	 * 
	 */
	public ParamTag() {
		// default constructor
	}

	/**
	 * @return String
	 */
	@Override
	public String getComponentType() {
		return COMPONENT_FAMILY;
	}

	/**
	 * @return String
	 */
	@Override
	public String getRendererType() {
		return null;
	}

	/**
	 * 
	 */
	@Override
	public void release() {
		super.release();
		this._name = null;
		this._value = null;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this._name = name;
	}

	/**
	 * @param value
	 */
	public void setValue(String value) {
		this._value = value;
	}

	/**
	 * @see javax.faces.webapp.UIComponentELTag#setProperties(javax.faces.component.UIComponent)
	 */
	@Override
	protected void setProperties(UIComponent comp) {
		try {
			// UIParameter comp = (UIParameter) component;
			super.setProperties(comp);
			FacesContext context = getFacesContext();
			ELContext el = context.getELContext();
			ExpressionFactory expFactory = context.getApplication().getExpressionFactory();
			if (this._name != null) {
				ValueExpression ve = expFactory.createValueExpression(this._name, Object.class);
				comp.setValueExpression(NAME_ATTR, ve);
			}
			if (this._value != null) {
				ValueExpression ve = expFactory
						.createValueExpression(el, this._value, Object.class);
				comp.setValueExpression(VALUE_ATTR, ve);
			}
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Component " + comp.getClass().getName()
					+ " is no javax.faces.component.UIParameter");
		}
	}

}